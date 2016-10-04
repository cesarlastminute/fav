package com.rumbo.favs.data.dao;

import com.rumbo.favs.data.entities.Airport;

public interface IAirportDao {

	public static final String IATACODE = "iataCode";
	public static final String CITY = "city";
	
	public Airport getAirportByIataCode(String iataCode);
	
}
