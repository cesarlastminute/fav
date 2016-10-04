package com.rumbo.favs.data.dao;

import com.rumbo.favs.business.bean.PassengerType;
import com.rumbo.favs.data.entities.DiscountByPassengerType;

public interface IDiscountByPassengerTypeDao {

	public static final String PASSENGERTYPE = "passengerType";
	public static final String DISCOUNTPERCENT = "discountPercent";
	
	public DiscountByPassengerType getDiscountPercent(PassengerType passengerType);
	
}
