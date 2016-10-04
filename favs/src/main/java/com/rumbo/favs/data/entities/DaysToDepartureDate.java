package com.rumbo.favs.data.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Itinerary
 */
@XmlRootElement(name = "daysToDepartureDate")
@XmlAccessorType (XmlAccessType.FIELD)
public class DaysToDepartureDate {

	private String min;
	private String max;
	private String discountPercent;
			
	public DaysToDepartureDate() {
		super();
	}

	public DaysToDepartureDate(String min, String max, String discountPercent) {
		super();
		this.min = min;
		this.max = max;
		this.discountPercent = discountPercent;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
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
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "DaysToDepartureDate [min=" + min + ", max=" + max + ", discountPercent=" + discountPercent + "]";
	}
	
}
