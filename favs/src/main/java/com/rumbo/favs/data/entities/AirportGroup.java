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
@XmlRootElement(name = "airportGroup")
@XmlAccessorType (XmlAccessType.FIELD)
public class AirportGroup {

	@XmlElement(name = "airport")
	List<Airport> airportGroup = new ArrayList<>();

	public List<Airport> getAirportGroup() {
		return airportGroup;
	}

	public void setAirportGroup(List<Airport> airportGroup) {
		this.airportGroup = airportGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airportGroup == null) ? 0 : airportGroup.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "AirportGroup [airportGroup=" + airportGroup + "]";
	}
	
}
