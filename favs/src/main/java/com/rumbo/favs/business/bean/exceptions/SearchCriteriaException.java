package com.rumbo.favs.business.bean.exceptions;

public class SearchCriteriaException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public static final String ERROR_DEPARTURE_CITY = "Departure city not exists";
	public static final String ERROR_DESTINATION_CITY = "Arrival city not exists";
	public static final String ERROR_DEPARTURE_DATE = "Departure city has to be greater than 0";
	public static final String ERROR_NUM_PASSENGERS = "Passengers have to be greater than 0";
	public static final String ERROR_NONE_PASSENGERS = "At least one passenger must flight";
			   
    public SearchCriteriaException(String errorType) {
        super(errorType);
    }    
    
	public String toString() {
    	
        return "Search Criteria Error: " + getMessage();
    }

}
