package cs545.airline.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.AirlineDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Flight;

@Named("airlines")
@ApplicationScoped
@Transactional
public class AirlineService {

	// These services should be evaluated to reconsider which methods should be
	// public

	@Inject
	private AirlineDao airlineDao;
	
	public String create(Airline airport) {
		airlineDao.create(airport);
		return "airlines";
	}

	public void delete(Airline airline) {
		if(airline != null)
		{
			for(Flight f: airline.getFlights())
				if(f!=null)new FlightService().delete(f);
				
		}
		airlineDao.delete(airline);
	}

	public Airline update(Airline airport) {
		return airlineDao.update(airport);
	}

	public Airline find(Airline airport) {
		return airlineDao.findOne(airport.getId());
	}

	public Airline findByName(String name) {
		return airlineDao.findOneByName(name);
	}

	public List<Airline> findByFlight(Flight flight) {
		return airlineDao.findByFlight(flight.getId());
	}

	public List<Airline> findAll() {
		return airlineDao.findAll();
	}
	
	public Airline getAirline(String name) {
		return new Airline(name);
	}
	
	public Airline findById(long id) {
		return airlineDao.findOne(id);
	}
	
	public List<Airline> getAll() {
		return airlineDao.findAll();
	}
			
}
