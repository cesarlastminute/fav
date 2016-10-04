package com.rumbo.favs.data.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Itinerary
 */
@XmlRootElement(name = "flight")
@XmlAccessorType (XmlAccessType.FIELD)
public class Flight {

	private String origin;
	private String destination;
	private String airline;
	private String basePrice;
			
	public Flight() {
		super();
	}

	public Flight(String origin, String destination, String airline, String basePrice) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.airline = airline;
		this.basePrice = basePrice;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getAirline() {
		return airline;
	}
	
	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	public String getBasePrice() {
		return basePrice;
	}
	
	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airline == null) ? 0 : airline.hashCode());
		result = prime * result + ((basePrice == null) ? 0 : basePrice.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Flight [origin=" + origin + ", destination=" + destination + ", airline=" + airline + ", basePrice=" + basePrice + "]";
	}
	
}
