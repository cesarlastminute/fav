package com.rumbo.favs.business.services;

import com.rumbo.favs.business.bean.AvailabilityResult;
import com.rumbo.favs.business.bean.exceptions.SearchCriteriaException;

/**
 * Main business interface
 */
public interface ISearchEngine {

	/**
	 * @return the search result
	 * */
	public AvailabilityResult search(String origin, String destination, int daysToDeparture, int numAdult, int numChild, int numInfant)  throws SearchCriteriaException;
	
}
