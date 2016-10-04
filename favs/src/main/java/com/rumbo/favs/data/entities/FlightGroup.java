package com.rumbo.favs.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class to allow work with object list and marshal
 * 
 */
@XmlRootElement(name = "flightGroup")
@XmlAccessorType (XmlAccessType.FIELD)
public class FlightGroup {

	@XmlElement(name = "flight")
	List<Flight> flightGroup = new ArrayList<>();

	public List<Flight> getFlightGroup() {
		return flightGroup;
	}

	public void setFlightGroup(List<Flight> flightGroup) {
		this.flightGroup = flightGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flightGroup == null) ? 0 : flightGroup.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Flights [flights=" + flightGroup + "]";
	}
	
}
