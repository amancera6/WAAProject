package edu.mum.cs545.ws;

import java.io.IOException;
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
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirportService;

@Named
@Path("Airport")
public class AirportRest {

	@Inject
	private AirportService airportService;
	private static final String RESULT_OK = "<result>success</result>";

	
	
	
	@DELETE
	@Path("/Delete/{id}")
	public String Delete(@PathParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		Airport airport = airportService.findById(id);
		airportService.delete(airport);
		return RESULT_OK;
	}


	@POST
	@Path("/Update")
	public Airport Update(Airport airport, @Context HttpServletResponse servletResponse) throws IOException {
		Airport airportup = airportService.update(airport);
		return airportup;
	}


	@GET
	@Path("/List")
	public List<Airport> getAirports() {
		return airportService.findAll();
	}

	@GET
	@Path("/FindByCode")
	public Airport FindBySrlnr(@QueryParam("airportcode") String airportcode,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(airportcode)) {
			return null;
		}
		Airport airportfind = airportService.findByCode(airportcode);
		return airportfind;
	}

	@GET
	@Path("/FindByCity")
	public List<Airport> FindByCity(@QueryParam("city") String city,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(city)) {
			return null;
		}
		return airportService.findByCity(city);
	}

	@GET
	@Path("/FindByCountry")
	public List<Airport> FindByCountry(@QueryParam("country") String country,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(country)) {
			return null;
		}
		return airportService.findByCountry(country);
	}

	@GET
	@Path("/FindByName")
	public List<Airport> FindByName(@QueryParam("name") String name,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(name)) {
			return null;
		}
		return airportService.findByName(name);
	}


	@GET
	@Path("/FindById")
	public Airport FindById(@QueryParam("id") long id, @Context HttpServletResponse servletResponse)
			throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airport airport = new Airport();
		airport.setId(id);
		Airport airportfind = airportService.find(airport);
		return airportfind;
	}
	
	@GET
	@Path("/FindByArrival")
	public List<Airport> FindByArrival(@QueryParam("id") long id, @Context HttpServletResponse servletResponse)
			throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = new Flight();
		flight.setId(id);
		return airportService.findByArrival(flight);
	}
	
	@GET
	@Path("/FindByDeparture")
	public List<Airport> FindByDeparture(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = new Flight();
		flight.setId(id);
		return airportService.findByDeparture(flight);
	}


	@GET
	@Path("/FindByName/{name}")
	public List<Airport> FindByName2(@PathParam("name") String name,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(name)) {
			return null;
		}
		return airportService.findByName(name);
	}
	
	@POST
	@Path("/new")
	public String Create(Airport airport, @Context HttpServletResponse servletResponse) throws IOException {
		airportService.create(airport);
		return RESULT_OK;
	}

}
