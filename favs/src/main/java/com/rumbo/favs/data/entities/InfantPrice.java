package com.rumbo.favs.data.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Infant Price by Airline
 */
@XmlRootElement(name = "infantPrice")
@XmlAccessorType (XmlAccessType.FIELD)
public class InfantPrice {

	private String iataCode;
	private String name;
	private String price;
	
	public InfantPrice() {
		super();
	}

	public InfantPrice(String iataCode, String name, String price) {
		super();
		this.iataCode = iataCode;
		this.name = name;
		this.price = price;
	}

	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iataCode == null) ? 0 : iataCode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "InfantPrice [iataCode=" + iataCode + ", name=" + name + ", price=" + price + "]";
	}	
	
}
