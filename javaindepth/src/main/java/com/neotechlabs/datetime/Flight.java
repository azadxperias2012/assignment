package com.neotechlabs.datetime;

import java.time.LocalDateTime;

public class Flight implements Comparable<Flight> {
	private int id; 
	private String flightNumber;
	private String airline;
	private String departureCity;
	private String arrivalCity;
	private LocalDateTime departureTime; // Local time at the time zone of the departure city
	private LocalDateTime arrivalTime; // Local time at the time zone of the arrival city
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public Flight(int id, String flightNumber, String airline, String departureCity, String arrivalCity,
			LocalDateTime departureTime, LocalDateTime arrivalTime) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.airline = airline;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		//System.out.println(departureCity + " to " + arrivalCity + " --> " + departureTime);
	}
	
	@Override
	public int compareTo(Flight flight) {
		return this.departureTime.compareTo(flight.departureTime);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(departureCity)
			.append(" --> ")
			.append(arrivalCity)
			.append(". Departure Time")
			.append(departureTime)
			.append(", Arrival Time")
			.append(arrivalTime);
		return sb.toString(); 
	}
}
