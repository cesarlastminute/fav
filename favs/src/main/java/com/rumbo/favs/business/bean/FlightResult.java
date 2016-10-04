package com.rumbo.favs.business.bean;

import java.util.List;

/**
 * Flight result
 * 
 * Flight and price breakdown list
 */
public class FlightResult {

	String flight;
	float totalAmount;	
	List<TravellerPrice> travellerPriceList;
		
	public FlightResult() {
		super();
	}

	public FlightResult(String flight, float totalAmount, List<TravellerPrice> travellerPriceList) {
		super();
		this.flight = flight;
		this.totalAmount = totalAmount;
		this.travellerPriceList = travellerPriceList;
	}

	public String getFlight() {
		return flight;
	}
	
	public void setFlight(String flight) {
		this.flight = flight;
	}
	
	public List<TravellerPrice> getTravellerPriceList() {
		return travellerPriceList;
	}
	
	public void setTravellerPriceList(List<TravellerPrice> travellerPriceList) {
		this.travellerPriceList = travellerPriceList;
	}
	
	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + Float.floatToIntBits(totalAmount);
		result = prime * result + ((travellerPriceList == null) ? 0 : travellerPriceList.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "FlightResult [flight=" + flight + ", totalAmount=" + totalAmount + ", travellerPriceList=" + travellerPriceList + "]";
	}
		
}
