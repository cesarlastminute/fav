package com.rumbo.favs.business.services;

import java.util.List;

import com.rumbo.favs.business.bean.FlightResult;
import com.rumbo.favs.business.bean.SearchCriteria;
import com.rumbo.favs.data.entities.FlightGroup;

public interface IFarePrice {

	/**
	 * @return price's breakdown
	 * 
	 * */
	public List<FlightResult> fare(SearchCriteria searchCriteria, FlightGroup flights);
	
}
