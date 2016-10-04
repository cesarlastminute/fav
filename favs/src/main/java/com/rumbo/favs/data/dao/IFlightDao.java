package com.rumbo.favs.data.dao;

import com.rumbo.favs.data.entities.FlightGroup;

public interface IFlightDao {
	
	public static final String ORIGIN = "origin";
	public static final String DESTINATION = "destination";
	public static final String AIRLINE = "airline";
	public static final String BASEPRICE = "basePrice";
	
	public FlightGroup getFlightsByItinerary(String origin, String destination);
	
}
