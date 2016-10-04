package com.rumbo.favs.data.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Airport
 */
@XmlRootElement(name = "airport")
@XmlAccessorType (XmlAccessType.FIELD)
public class Airport {

	private String iataCode;
	private String city;
	
	public Airport() {
		super();
	}
	
	public Airport(String iataCode, String city) {
		super();
		this.iataCode = iataCode;
		this.city = city;
	}

	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((iataCode == null) ? 0 : iataCode.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Airport [iataCode=" + iataCode + ", city=" + city + "]";
	}
		
}
