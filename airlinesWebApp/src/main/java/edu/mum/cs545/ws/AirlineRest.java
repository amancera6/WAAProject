package edu.mum.cs545.ws;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;

@Named
@Path("Airline")
public class AirlineRest {

	@Inject
	private AirlineService airlineService;
	private static final String RESULT_OK = "<result>success</result>";
	
	
	@GET
	@Path("/FindByFlight")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Airline> findByFlightAirline(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = new Flight();
		flight.setId(id);
		return airlineService.findByFlight(flight);
	}
	
	//Create a new Airline
	@POST
	@Path("/New")
	public String Create(Airline airline, @Context HttpServletResponse servletResponse) throws IOException {
		airlineService.create(airline);
		return RESULT_OK;
	}
	
	//Delete a new Airline
	@DELETE
	@Path("/Delete/{id}")
	public String Delete(@PathParam("id") long id, @Context HttpServletResponse servletResponse) throws IOException {
		Airline airline = airlineService.findById(id);
		airlineService.delete(airline);
		return RESULT_OK;
	}

	//Update an Airline
	@POST
	@Path("/Update")
	public Airline Update(Airline airline, @Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS UPDATE JSON: " + airline);
		Airline airlineup = airlineService.update(airline);
		return airlineup;
	}
	
	@Path("/All")
	@GET
	public List<Airline> getAirlines() {
		return airlineService.getAll();
	}	
	
	@GET
	@Path("/FindByName")
	public Airline FindByName(@QueryParam("name") String name,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(name)) {
			return null;
		}
		Airline airlinefind = airlineService.findByName(name);
		return airlinefind;
	}

	@GET
	@Path("/FindById")
	public Airline FindById(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airline airline = new Airline();
		airline.setId(id);
		Airline airlinefind = airlineService.find(airline);
		return airlinefind;
	}
	
	
	
	




}
