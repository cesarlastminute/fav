package com.rumbo.favs.data.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Itinerary
 */
@XmlRootElement(name = "discountByPassengerType")
@XmlAccessorType (XmlAccessType.FIELD)
public class DiscountByPassengerType {

	private String passengerType;
	private String discountPercent;
			
	public DiscountByPassengerType() {
		super();
	}	

	public DiscountByPassengerType(String passengerType, String discountPercent) {
		super();
		this.passengerType = passengerType;
		this.discountPercent = discountPercent;
	}

	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discountPercent == null) ? 0 : discountPercent.hashCode());
		result = prime * result + ((passengerType == null) ? 0 : passengerType.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "DiscountByPassengerType [passengerType=" + passengerType + ", discountPercent=" + discountPercent + "]";
	}
	
}
