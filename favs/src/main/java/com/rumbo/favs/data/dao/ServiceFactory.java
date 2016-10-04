package com.rumbo.favs.data.dao;

import com.rumbo.favs.data.dao.impl.AirportDaoImpl;
import com.rumbo.favs.data.dao.impl.ApplicationConfigurationByPassengerTypeDaoImpl;
import com.rumbo.favs.data.dao.impl.DaysToDepartureDateDaoImpl;
import com.rumbo.favs.data.dao.impl.DiscountByPassengerTypeDaoImpl;
import com.rumbo.favs.data.dao.impl.FlightDaoImpl;
import com.rumbo.favs.data.dao.impl.InfantPriceDaoImpl;

public class ServiceFactory {

	public static IFlightDao getFlightDaoFactory() {
		return new FlightDaoImpl();
	}
	
	public static IInfantPricesDao getInfantPricesDaoFactory() {
		return new InfantPriceDaoImpl();
	}
	
	public static IApplicationConfigurationByPassengerTypeDao getApplicationConfigurationByPassengerTypeDaoFactory() {
		return new ApplicationConfigurationByPassengerTypeDaoImpl();
	}
	
	public static IDaysToDepartureDateDao getDaysToDepartureDateDaoFactory() {
		return new DaysToDepartureDateDaoImpl();
	}
	
	public static IDiscountByPassengerTypeDao getDiscountByPassengerTypeDaoFactory() {
		return new DiscountByPassengerTypeDaoImpl();
	}
	
	public static IAirportDao getAirportDaoFactory() {
		return new AirportDaoImpl();
	}
	
}
