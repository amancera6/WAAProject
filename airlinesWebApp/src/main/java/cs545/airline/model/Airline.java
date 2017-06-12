package cs545.airline.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Named("airlineEntity")
@SessionScoped
@Entity
@Table(uniqueConstraints=@UniqueConstraint(name="Airline_Name",columnNames={"name"}))
public class Airline implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany(mappedBy = "airline")
	@OrderBy("departureDate, departureTime")
	private List<Flight> flights;
	

	/* Constructors */
	public Airline() {
	}

	public Airline(String name) {
		this.name = name;
	}

	/* Getters & Setters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Flight> getFlights() {
		return Collections.unmodifiableList(flights);
	}
	
	/* Collections Methods */
	public String addFlight(Flight flight) {
		if(flights == null)
			flights = new ArrayList<>();
		boolean success =  (!flights.contains(flight)) && (flights.add(flight));
		if (success) {
			flight.setAirline(this);
		}
		//this.number = flights.size();
		return null;
	}

	public String removeFlight(Flight flight) {
		if(flights == null)
			return null;
		if (flights.remove(flight)) {
			flight.setAirline(null);
		}
		return null;
	}
	
	public String addItem(String number,String initialDate,String initialTime,String endDate,String endTime,
			Airline airline,Airport departure,Airport destination)
	{
		System.out.println("R");
		return null;
	}
}
