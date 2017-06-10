package cs545.airline.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.AirlineDao;
import cs545.airline.model.Airline;

@Named("airlinesHelper")
@ApplicationScoped
@Transactional
public class myServiceImplementation {
	
	@Inject
	private AirlineDao airlineDao;
	
	
	public List<Airline> getAll() {
		return airlineDao.findAll();
	}
	
	
}
