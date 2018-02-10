package com.neotechlabs.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class FlightFinder {
	private Map<String, List<Flight>> allFlights = new HashMap<>();

	public FlightFinder(Map<String, List<Flight>> allFlights) {
		this.allFlights = allFlights;
	}

	public List<NavigableSet<Flight>> findFlights(int dayOfMonth, int month, int year, 
	        int preferredDepartureStartHour, int preferredDepartureEndHour, 
	        String departureCity, String arrivalCity, String finalArrivalCity,
			String departureCityTimeZone, String arrivalCityTimeZone) {
		
		List<NavigableSet<Flight>> result = new ArrayList<>();
        
        // Step 1: Construct ZonedDateTime objects to represent User-specified time interval when flights depart
		LocalDate departureDate = LocalDate.of(2017, month, dayOfMonth);
		ZonedDateTime departureStartTime = ZonedDateTime.of(departureDate, LocalTime.of(9, 00), ZoneId.of(departureCityTimeZone));		
		ZonedDateTime departureEndTime = ZonedDateTime.of(departureDate, LocalTime.of(13, 00), ZoneId.of(departureCityTimeZone));
		ZonedDateTime departureStartTimeAtConnectingCity = ZonedDateTime.of(departureDate, LocalTime.of(9, 00), ZoneId.of(arrivalCityTimeZone));		
		ZonedDateTime departureEndTimeAtConnectingCity = ZonedDateTime.of(departureDate, LocalTime.of(13, 00), ZoneId.of(arrivalCityTimeZone));

        // Step 2: Find departing flights at departureCity
        List<Flight> allDepartingFlights = allFlights.get(departureCity);
        
        NavigableSet<Flight> departingflights = new TreeSet<>();
                
        // Your code
        for (Flight flight : allDepartingFlights) {
            // Tip: Methods like isAfter can be used to find flights
        	// in the specified user time interval
			if (flight.getArrivalCity().compareTo(arrivalCity) == 0) {
				ZonedDateTime atZone = flight.getDepartureTime().atZone(ZoneId.of(departureCityTimeZone));
				if (atZone.isAfter(departureStartTime) && atZone.isBefore(departureEndTime)) {
					departingflights.add(flight);
				}
			}
		}
        
        // Step 3: Find connecting flights
        //   Constraint 1: Departing at arrivalCity (e.g., Dubai) and arrive at finalArrivalCity (e.g., Mumbai)
        //   Constraint 2: Should start at least two hours after the arrival time of the first flight in the above navigable set

        List<Flight> allConnectingFlights = allFlights.get(arrivalCity);        
        
        NavigableSet<Flight> connectingflights = new TreeSet<>();      

        for (Flight flight : allConnectingFlights) {
        	if (flight.getArrivalCity().compareTo(finalArrivalCity) == 0) {
        		ZonedDateTime atZone = flight.getDepartureTime().atZone(ZoneId.of(arrivalCityTimeZone));
        		if (departureStartTimeAtConnectingCity.until(atZone, ChronoUnit.MINUTES) >= 120 || departureEndTimeAtConnectingCity.until(atZone, ChronoUnit.MINUTES) >= 120) {
        			connectingflights.add(flight);
				}
        	}
        }
        
        result.add(departingflights);
        result.add(connectingflights);
        
        return result;
	}
	
	public static void main(String[] args) {
	    Flight f1 = new Flight(1, "1", "Emirates", "New York", "Dubai",
			LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f2 = new Flight(2, "2", "Delta", "San Francisco", "Paris",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f3 = new Flight(3, "3", "Delta", "San Francisco", "Rome",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f4 = new Flight(4, "4", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 8, 0), LocalDateTime.of(2017, 12, 20, 8, 0));
		Flight f5 = new Flight(5, "5", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 9, 30), LocalDateTime.of(2017, 12, 20, 9, 30));
		Flight f6 = new Flight(6, "6", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 11, 30), LocalDateTime.of(2017, 12, 20, 11, 30));
		Flight f7 = new Flight(7, "7", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 12, 30), LocalDateTime.of(2017, 12, 20, 12, 30));
		Flight f12 = new Flight(12, "12", "Emirates", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20, 10, 30), LocalDateTime.of(2017, 12, 20, 10, 30));
		Flight f13 = new Flight(13, "13", "Emirates", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20, 12, 0), LocalDateTime.of(2017, 12, 20, 12, 0));
		Flight f14 = new Flight(14, "14", "Emirates", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20, 14, 0), LocalDateTime.of(2017, 12, 20, 14, 0));
		
		Map<String, List<Flight>> allFlights = new HashMap<>();
		
		allFlights.put("New York", Arrays.asList(f1));
		allFlights.put("San Francisco", Arrays.asList(f2, f3, f4, f5, f6, f7));
		allFlights.put("Dubai", Arrays.asList(f12, f13, f14));
		
		List<NavigableSet<Flight>> result = new FlightFinder(allFlights).findFlights(20, 12, 2017, 9, 13, "San Francisco", "Dubai", "Mumbai", "America/Los_Angeles", "Asia/Dubai");
		NavigableSet<Flight> departingFlights = result.get(0);
		for (Flight flight : departingFlights) {
			System.out.println(flight);
		}
		NavigableSet<Flight> connectingflights = result.get(1);
		for (Flight flight : connectingflights) {
			System.out.println(flight);
		}
	}
}
