package edu.mum.cs545.ws;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;

@Named
@Path("Airplane")
public class AirplaneRest {

	@Inject
	private AirplaneService airplaneService;
	private static final String RESULT_OK = "<result>success</result>";	

	
	

	@DELETE
	@Path("/Delete/{id}")
	public String Delete(@PathParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		Airplane airplane =airplaneService.findById(id);
		airplaneService.delete(airplane);
		return RESULT_OK;
	}
	
	
	
	@POST
	@Path("/Update")
	public Airplane Update(	Airplane airplane, @Context HttpServletResponse servletResponse) throws IOException {
		Airplane airplaneup = airplaneService.update(airplane);
		return airplaneup;
	}
	

	@Path("/List")
	@GET
	public List<Airplane> getAirplanes() {
		return airplaneService.findAll();
	}
	
	@GET
	@Path("/FindBySrlnr")
	public Airplane FindBySrlnr(@QueryParam("serialnr") String serialnr,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(serialnr)) {
			return null;
		}
		Airplane airlinefind = airplaneService.findBySrlnr(serialnr);
		return airlinefind;
	}

	
	@GET
	@Path("/FindById")
	public Airplane FindById(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airplane airplane = new Airplane();
		airplane.setId(id);
		Airplane airlinefind = airplaneService.find(airplane);
		return airlinefind;
	}
	
	@GET
	@Path("/FindByFlight")
	public List<Airplane> findByFlight(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = new Flight();
		flight.setId(id);
		return airplaneService.findByFlight(flight);
	}
	

	@GET
	@Path("/FindByModel")
	public List<Airplane> FindByModel(@QueryParam("model") String model,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(model)) {
			return null;
		}
		Airplane airplane = new Airplane();
		airplane.setModel(model);
		return airplaneService.findByModel(model);
	}
	

	@POST
	@Path("/Create")
	public String Create(Airplane airplane, @Context HttpServletResponse servletResponse) throws IOException {
		airplaneService.create(airplane);
		return RESULT_OK;
	}


	

}
