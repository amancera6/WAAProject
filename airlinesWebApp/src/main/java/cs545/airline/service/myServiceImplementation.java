package cs545.airline.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.AirlineDao;
import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Flight;


public class myServiceImplementation {
	
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,Locale.US);
	private static DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,
			Locale.US);
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException
	{
		try
		{
			String s = "2015-08-09";
			String[] d = s.split("-");
			Date initialD = df.parse(d[1] + "/" + d[2] + "/" + d[0]);
			String time = "15:00";
			d = time.split(":");
			Date t;
			int hour =Integer.valueOf(d[0]); 
			if( hour > 12)
				t = tf.parse(hour - 12 + ":" +d[1] + " PM");
			else if(Integer.valueOf(d[0]) == 12)
				t = tf.parse(d[0] + ":" + d[1] +" PM");
			else
				t = tf.parse(d[0] + ":" + d[1] +" AM");	
			
		
			System.out.println(initialD);
			System.out.println(t);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}
	
}
