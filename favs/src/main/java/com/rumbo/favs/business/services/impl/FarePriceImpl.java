package com.rumbo.favs.business.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.omg.PortableInterceptor.ACTIVE;

import com.rumbo.favs.business.bean.ApplicationConfigurationType;
import com.rumbo.favs.business.bean.BreakDownPrice;
import com.rumbo.favs.business.bean.FlightResult;
import com.rumbo.favs.business.bean.PassengerType;
import com.rumbo.favs.business.bean.SearchCriteria;
import com.rumbo.favs.business.bean.TravellerPrice;
import com.rumbo.favs.business.services.IFarePrice;
import com.rumbo.favs.data.dao.IApplicationConfigurationByPassengerTypeDao;
import com.rumbo.favs.data.dao.IDaysToDepartureDateDao;
import com.rumbo.favs.data.dao.IDiscountByPassengerTypeDao;
import com.rumbo.favs.data.dao.IInfantPricesDao;
import com.rumbo.favs.data.dao.ServiceFactory;
import com.rumbo.favs.data.entities.ApplicationConfigurationByPassengerType;
import com.rumbo.favs.data.entities.DaysToDepartureDate;
import com.rumbo.favs.data.entities.DiscountByPassengerType;
import com.rumbo.favs.data.entities.Flight;
import com.rumbo.favs.data.entities.FlightGroup;
import com.rumbo.favs.data.entities.InfantPrice;

/**
 * Calculate fare price
 * 
 */
public class FarePriceImpl implements IFarePrice{
	
	//DAOS to use
	
	private final IApplicationConfigurationByPassengerTypeDao applicationConfigurationByPassengerTypeDao = 
			ServiceFactory.getApplicationConfigurationByPassengerTypeDaoFactory();
	
	private final IDiscountByPassengerTypeDao discountByPassengerTypeDao = 
			ServiceFactory.getDiscountByPassengerTypeDaoFactory();
	
	private final IDaysToDepartureDateDao daysToDepartureDateDao = 
			ServiceFactory.getDaysToDepartureDateDaoFactory();
	
	private final IInfantPricesDao infantPricesDao = 
			ServiceFactory.getInfantPricesDaoFactory();
	

	private String active = "1";
	
	/**
	 * Return price's breakdown
	 * 
	 * @return List<FlightResult> flightResultList
	 * */
	public List<FlightResult> fare(SearchCriteria searchCriteria, FlightGroup flights){
		
		List<FlightResult> flightResultList = new ArrayList<>();
		
		// Iterate all possible flights
		for (Flight flight : flights.getFlightGroup()){			
			FlightResult flightResult = getFlightResult(searchCriteria, flight);
			if (flightResult != null){
				flightResultList.add(flightResult);
			}			
		}
		
		return flightResultList;
	}
	
	private FlightResult getFlightResult(SearchCriteria searchCriteria, Flight flight){
		
		String flightNumber = flight.getAirline();
		String airLine = getAirline(flight);		
		Float basePrice = Float.parseFloat(flight.getBasePrice());	
		Float totalAmount = 0f;
		
		List<TravellerPrice> travellerPriceList = getTravellerPriceList(airLine, basePrice, searchCriteria);
				
		if (travellerPriceList != null && travellerPriceList.size() > 0){
			totalAmount = getTotalAmountByFlight(travellerPriceList);	
		}
		
		return new FlightResult(flightNumber, totalAmount, travellerPriceList);		
	}
	
	
	private float getTotalAmountByFlight(List<TravellerPrice> travellerPriceList){
			
		float totalAmount = 0f;
		
		for (TravellerPrice travellerPrice: travellerPriceList){
			totalAmount += travellerPrice.getTotalAmount();
		}
		
		return totalAmount;
	}
	
	/**
	 * Get an airline from flight
	 * 
	 * @return String airline 
	 * */
	private String getAirline(Flight flight){
		
		return flight.getAirline().substring(0,2);
	}
	
	/**
	 * Get a list for each passenger type
	 * with their breakdown price
	 * 
	 * @return List<TravellerPrice> travellerPriceList 
	 * */
	private List<TravellerPrice> getTravellerPriceList(String airline, Float basePrice, SearchCriteria searchCriteria){
		
		List<TravellerPrice> travellerPriceList = new ArrayList<>();
		
		// Iterate for each passenger type
		for (Map.Entry<PassengerType, Integer> entry : searchCriteria.getPassengers().entrySet()) {
			PassengerType passengerType = entry.getKey();
			Integer num = entry.getValue();
			
			// Exist this passenger type
			if(num > 0){
				// Get a base price type from application configuration for a passenger type
				// I am trying a generic solution			
				ApplicationConfigurationByPassengerType applicationConfigurationByPassengerType = 
						applicationConfigurationByPassengerTypeDao.getApplicationConfigurationByName(ApplicationConfigurationType.INFANTBASEPRICE);
				// If apply a reduce fare set base price new value
				// else airline fare is ok
				if (applicationConfigurationByPassengerType != null){
					if (active.equals(applicationConfigurationByPassengerType.getStatus()) &&
							active.equals(applicationConfigurationByPassengerType.getPropertyValuByPassengerTypepassengerType(passengerType))){
						// Get reduce fare (infant base price)					
						InfantPrice infantPrice = infantPricesDao.getInfantPriceByAirline(airline);
						
						if (infantPrice != null && infantPrice.getPrice() != null && !infantPrice.getPrice().isEmpty()){
							basePrice = Float.parseFloat(infantPrice.getPrice());
						}
					}					
				}
				travellerPriceList.add(
						new TravellerPrice(passengerType, num, getBreakDownPrice(airline, passengerType, basePrice, searchCriteria.getDaysToDeparture())));			
			}
		}	
		
		if (travellerPriceList.size() > 0){
			setTotalAmountByPassengerType(travellerPriceList);
		}
		
		return travellerPriceList;		
	}
	
	private void setTotalAmountByPassengerType(List<TravellerPrice> travellerPriceList){
				
		for (TravellerPrice travellerPrice: travellerPriceList){
			getTravellerPriceAmount(travellerPrice);
		}
	}
	
	private void getTravellerPriceAmount(TravellerPrice travellerPrice){
		
		if (travellerPrice != null && travellerPrice.getNumber() > 0){
			
			float amount = 0;
			if(travellerPrice.getBreakDownPrice().getDateDiscount() > 0){
				amount = (float) (travellerPrice.getBreakDownPrice().getDateDiscount() * travellerPrice.getBreakDownPrice().getBasePrice()) / 100;
			}else{
				amount = travellerPrice.getBreakDownPrice().getBasePrice();
			}
			if (travellerPrice.getBreakDownPrice().getPassengerDiscount() > 0){
				amount =  (float) ((100 - travellerPrice.getBreakDownPrice().getPassengerDiscount()) * amount) / 100;
			}
			
			travellerPrice.setTotalAmount(amount * travellerPrice.getNumber());
		}
	}
	
	/**
	 * Get a breakdown price for a passenger type
	 * with their possible discounts
	 * 
	 * @return BreakDownPrice breakDownPrice 
	 * */
	private BreakDownPrice getBreakDownPrice(String airline, PassengerType passengerType, Float basePrice, int daysToDeparture){
		
		float passengerDiscount = 0;
		float dateDiscount = 0; 
		
		// Days to departure date discount
		ApplicationConfigurationByPassengerType applicationConfigurationByPassengerType = 
				applicationConfigurationByPassengerTypeDao.getApplicationConfigurationByName(ApplicationConfigurationType.DAYSTODEPARTUREDATE);		
		//Active discount && apply discount for this passenger type
		if (applicationConfigurationByPassengerType != null){
			if (active.equals(applicationConfigurationByPassengerType.getStatus()) &&
					active.equals(applicationConfigurationByPassengerType.getPropertyValuByPassengerTypepassengerType(passengerType))){
				
				// Get info discount
				DaysToDepartureDate daysToDepartureDate = daysToDepartureDateDao.getDiscountPercent(daysToDeparture);
				
				if(daysToDepartureDate != null && !daysToDepartureDate.getDiscountPercent().isEmpty()){
					dateDiscount = Float.parseFloat(daysToDepartureDate.getDiscountPercent());
				}				
			}
		}
		
		// Discount by passenger type
		applicationConfigurationByPassengerType = 
				applicationConfigurationByPassengerTypeDao.getApplicationConfigurationByName(ApplicationConfigurationType.DISCOUNTBYPASSENERTYPE);		
		//Active discount && apply discount for this passenger type
		if (applicationConfigurationByPassengerType != null){
			if (active.equals(applicationConfigurationByPassengerType.getStatus()) &&
					active.equals(applicationConfigurationByPassengerType.getPropertyValuByPassengerTypepassengerType(passengerType))){
				
				// Get info discount
				DiscountByPassengerType discountByPassengerType = discountByPassengerTypeDao.getDiscountPercent(passengerType);
				
				if(discountByPassengerType != null && !discountByPassengerType.getDiscountPercent().isEmpty()){
					passengerDiscount = Float.parseFloat(discountByPassengerType.getDiscountPercent());
				}	
			}
		}
				
		return new BreakDownPrice(basePrice, passengerDiscount, dateDiscount);
	}
		
}
