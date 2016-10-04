package com.rumbo.favs.data.dao;

import com.rumbo.favs.data.entities.InfantPrice;

public interface IInfantPricesDao {

	public static final String IATACODE = "iataCode";
	public static final String NAME = "name";
	public static final String PRICE = "price";
	
	public InfantPrice getInfantPriceByAirline(String airline);
	
}
