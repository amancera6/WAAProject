package edu.mum.cs545.ws;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

@Named
@Path("Flight")
public class FlightRest {

	@Inject
	private FlightService flightService;
	private static final String RESULT_OK = "<result>success</result>";
	

	@POST
	@Path("/new")
	public String Create(Flight flight, @Context HttpServletResponse servletResponse) throws IOException {
		flightService.create(flight);
		return RESULT_OK;
	}
	
	
	@DELETE
	@Path("/delete/{id}")
	public String Delete(@PathParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		Flight flight = flightService.findById(id);
		flightService.delete(flight);
		return RESULT_OK;
	}

	
	@POST
	@Path("/update")
	public Flight Update(Flight flight, @Context HttpServletResponse servletResponse) throws IOException {
		return flightService.update(flight);
	}
	
	@GET
	@Path("/all")
	public List<Flight> getAll() {
		return flightService.findAll();
	}


	@GET
	@Path("/findByNumber")
	public List<Flight> FindByNumber(@QueryParam("Flightnr") String flightnr,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(flightnr)) {
			return null;
		}
		return flightService.findByNumber(flightnr);
	}

	

	@GET
	@Path("/findByArrivalBetween")
	public List<Flight> FindByArrivalBetween(@QueryParam("datetimeFrom") String datetimeFrom,
			@QueryParam("datetimeTo") String datetimeTo, @Context HttpServletResponse servletResponse)
			throws IOException {
		if ("".equals(datetimeFrom) || "".equals(datetimeTo)) {
			return null;
		}
		 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		try {
			return flightService.findByArrivalBetween(formatter.parse(datetimeFrom), formatter.parse(datetimeTo));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/findByArrivalDate")
	public List<Flight> findByArrival(@QueryParam("datetime") String datetime,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(datetime)) {
			return null;
		}
		  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		  
		try {
			return flightService.findByArrival(formatter.parse(datetime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/findByDeparture")
	public List<Flight> findByDeparture(@QueryParam("datetime") String datetime,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(datetime)) {
			return null;
		}
		  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		  
		try {
			return flightService.findByDeparture(formatter.parse(datetime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/FindByDepartureBetween")
	public List<Flight> findByDepartureBetween(@QueryParam("datetimeFrom") String datetimeFrom,
			@QueryParam("datetimeTo") String datetimeTo, @Context HttpServletResponse servletResponse)
			throws IOException {
		if ("".equals(datetimeFrom) || "".equals(datetimeTo)) {
			return null;
		}
		 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		try {
			return flightService.findByDepartureBetween(formatter.parse(datetimeFrom), formatter.parse(datetimeTo));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/byId")
	public Flight FindById(@QueryParam("id") long id, 
			@Context HttpServletResponse servletResponse)
			throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = new Flight();
		flight.setId(id);
		return flightService.find(flight);
	}
	
	@GET
	@Path("/byAirline")
	public List<Flight> findByAirlineFlight(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airline airline = new Airline();
		airline.setId(id);
		return flightService.findByAirline(airline);
	}
	
	@GET
	@Path("/FindByOrigin")
	public List<Flight> findByOriginFlight(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airport airport = new Airport();
		airport.setId(id);
		return flightService.findByOrigin(airport);
	}
	
	
	
	
	@GET
	@Path("/byArrivalPlane")
	public List<Flight> findByArrival(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airplane airplane = new Airplane();
		airplane.setId(id);
		return flightService.findByArrival(airplane);
	}
	
	@GET
	@Path("/byDestination")
	public List<Flight> findByDestination(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airport airport = new Airport();
		airport.setId(id);
		return flightService.findByDestination(airport);
	}
	
}
