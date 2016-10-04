package com.rumbo.favs.data.dao;

import com.rumbo.favs.data.entities.DaysToDepartureDate;

public interface IDaysToDepartureDateDao {

	public static final String MIN = "min";
	public static final String MAX = "max";
	public static final String DISCOUNTPERCENT = "discountPercent";
	
	public DaysToDepartureDate getDiscountPercent(int days);
	
}
