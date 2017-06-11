package cs545.airline.service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;

@Named("flights")
@ApplicationScoped
@Transactional
public class FlightService {
	
	@Inject
	private FlightDao flightDao;
	
	private List<Flight> flightList;
	private List<Flight> filteredFlightList;
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
			Locale.US);
	private static DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,
			Locale.US);
	// These services should be evaluated to reconsider which methods should be public 
	
	public void create(Flight flight) {
		flightDao.create(flight);
	}

	public void delete(Flight flight) {
		flightDao.delete(flight);
	}

	public Flight update(Flight flight) {
		return flightDao.update(flight);
	}
		
	public Flight find(Flight flight) {
		return flightDao.findOne(flight.getId());
	}

	public List<Flight> findByNumber(String flightnr) {
		return flightDao.findByFlightnr(flightnr);
	}

	public List<Flight> findByAirline(Airline airline) {
		return flightDao.findByAirline(airline.getId());
	}

	public List<Flight> findByOrigin(Airport airport) {
		return flightDao.findByOrigin(airport.getId());
	}

	public List<Flight> findByDestination(Airport airport) {
		return flightDao.findByDestination(airport.getId());
	}

	public List<Flight> findByArrival(Airplane airplane) {
		return flightDao.findByAirplane(airplane.getId());
	}

	public List<Flight> findByArrival(Date datetime) {
		return flightDao.findByArrival(datetime, datetime);
	}

	public List<Flight> findByArrivalBetween(Date datetimeFrom, Date datetimeTo) {
		return flightDao.findByArrivalBetween(datetimeFrom, datetimeFrom, datetimeTo, datetimeTo);
	}

	public List<Flight> findByDeparture(Date datetime) {
		return flightDao.findByDeparture(datetime, datetime);
	}

	public List<Flight> findByDepartureBetween(Date datetimeFrom, Date datetimeTo) {
		return flightDao.findByDepartureBetween(datetimeFrom, datetimeFrom, datetimeTo, datetimeTo);
	}

	public List<Flight> findAll() {
		return flightDao.findAll();
	}
	
	public List<Flight> getAll() {
		List<Flight> l = flightDao.findAll();
		for(Flight f:l)
			System.out.println(f);
		return l;
	}

	public FlightDao getFlightDao() {
		return flightDao;
	}

	public void setFlightDao(FlightDao flightDao) {
		this.flightDao = flightDao;
	}

	public List<Flight> getFlightList() {
		flightList = getAll();
		return flightList;
	}

	public void setFlightList(List<Flight> flightList) {
		this.flightList = flightList;
	}
	

	public List<Flight> getFilteredFlightList() {
		if(filteredFlightList == null)
			filteredFlightList = getFlightList();
		return filteredFlightList;
	}

	public void setFilteredFlightList(List<Flight> filteredFlightList) {
		this.filteredFlightList = filteredFlightList;
	}
	
	public Flight findById(long id) {
		return flightDao.findOneById(id);
	}
	
	@SuppressWarnings("deprecation")
	public String filterFlight(String initialDate,String initialTime,String endDate,String endTime,
			String airline,String departure,String destination)
	{
		boolean added = false;
		List<Flight> localList =  new ArrayList<>();
		
		try
		{
			if(flightList != null)
			{
				for(Flight f: flightList)
				{
					//Airline Validation
					if(!airline.isEmpty())
					{
						if(f.getAirline().getName().equals(airline))
							added = true;
						else continue;
					}
					//Destination Airport Validation
					if(!destination.isEmpty())
					{
						if(f.getDestination().getName().equals(destination))
							added = true;
						else continue;
					}
					//Departure Airport Validation
					if(!departure.isEmpty())
					{
						if(f.getOrigin().getName().equals(departure))
							added = true;
						else continue;
					}
					//Date Validation
					//look for between case
					if(!initialDate.isEmpty() && !endDate.isEmpty())
					{
						String[] d = initialDate.split("-");
						Date initialD = df.parse(d[1] + "/" + d[2] + "/" + d[0]);
						d = endDate.split("-");
						Date endD = df.parse(d[1] + "/" + d[2] + "/" + d[0]);
						Date flightArrivalDate = df.parse(f.getArrivalDate());
						Date flightDepartureDate = df.parse(f.getDepartureDate());
						if((flightDepartureDate.compareTo(initialD) >= 0 && flightDepartureDate.compareTo(endD) <= 0) 
								|| (flightArrivalDate.compareTo(initialD) >= 0 && flightArrivalDate.compareTo(endD) <= 0))
							added = true;
						else
							continue;
					}
					//Initial Date Validation
					else if(!initialDate.isEmpty())
					{
						String[] d = initialDate.split("-");
						Date initialD = df.parse(d[1] + "/" + d[2] + "/" + d[0]);
						Date flightArrivalDate = df.parse(f.getArrivalDate());
						Date flightDepartureDate = df.parse(f.getDepartureDate());
						if(flightDepartureDate.compareTo(initialD) == 0 || flightArrivalDate.compareTo(initialD) == 0)
							added = true;
						else
							continue;
					}
					//End Date Validation
					else if(!endDate.isEmpty())
					{
						String[] d = endDate.split("-");
						Date endD = df.parse(d[1] + "/" + d[2] + "/" + d[0]);						
						Date flightArrivalDate = df.parse(f.getArrivalDate());
						Date flightDepartureDate = df.parse(f.getDepartureDate());
						if(flightDepartureDate.compareTo(endD) == 0 || flightArrivalDate.compareTo(endD) == 0)
							added = true;
						else
							continue;
					}
					
					//Hour Validation
					//Hour between validation
					if(!initialTime.isEmpty() && !endTime.isEmpty())
					{
						Date initialT;
						String[] d = initialTime.split(":"); 
						int hour =Integer.valueOf(d[0]); 
						if( hour > 12)
							initialT = tf.parse(hour - 12 + ":" +d[1] + " PM");
						else if(Integer.valueOf(d[0]) == 12)
							initialT = tf.parse(d[0] + ":" + d[1] +" PM");
						else
							initialT = tf.parse(d[0] + ":" + d[1] +" AM");
						
						Date endT;
						d = endTime.split(":"); 
						hour =Integer.valueOf(d[0]); 
						if( hour > 12)
							endT = tf.parse(hour - 12 + ":" +d[1] + " PM");
						else if(Integer.valueOf(d[0]) == 12)
							endT = tf.parse(d[0] + ":" + d[1] +" PM");
						else
							endT = tf.parse(d[0] + ":" + d[1] +" AM");	
						
						Date flightArrivalTime = tf.parse(f.getArrivalTime());
						Date flightDepartureTime = tf.parse(f.getDepartureTime());
						if((flightArrivalTime.compareTo(initialT) >= 0 && flightArrivalTime.compareTo(endT) <= 0) 
								|| (flightDepartureTime.compareTo(initialT) >= 0 && flightDepartureTime.compareTo(endT) <= 0))
							added = true;
						else
							continue;
					}
					//Initial Time Validation
					else if(!initialTime.isEmpty())
					{
						Date initialT;
						String[] d = initialTime.split(":"); 
						int hour =Integer.valueOf(d[0]); 
						if( hour > 12)
							initialT = tf.parse(hour - 12 + ":" +d[1] + " PM");
						else if(Integer.valueOf(d[0]) == 12)
							initialT = tf.parse(d[0] + ":" + d[1] +" PM");
						else
							initialT = tf.parse(d[0] + ":" + d[1] +" AM");
						Date flightArrivalTime = tf.parse(f.getArrivalTime());
						Date flightDepartureTime = tf.parse(f.getDepartureTime());
						if(flightArrivalTime.compareTo(initialT) == 0 || flightDepartureTime.compareTo(initialT) == 0)
							added = true;
						else
							continue;
					}
					//End Time Validation
					else if(!endTime.isEmpty())
					{
						Date endT;
						String[] d = endTime.split(":"); 
						int hour =Integer.valueOf(d[0]); 
						if( hour > 12)
							endT = tf.parse(hour - 12 + ":" +d[1] + " PM");
						else if(Integer.valueOf(d[0]) == 12)
							endT = tf.parse(d[0] + ":" + d[1] +" PM");
						else
							endT = tf.parse(d[0] + ":" + d[1] +" AM");	
						Date flightArrivalTime = tf.parse(f.getArrivalTime());
						Date flightDepartureTime = tf.parse(f.getDepartureTime());
						if(flightArrivalTime.compareTo(endT) == 0 || flightDepartureTime.compareTo(endT) == 0)
							added = true;
						else
							continue;
					}
					
					if(added)
						localList.add(f);
					
				}
				
				if(localList.size()>0)
					filteredFlightList = localList;
				else
					filteredFlightList = flightList;
			}
		}catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
				
		return "showFlights";
	}

}
