package com.rumbo.favs.business.bean;

import java.text.DecimalFormat;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Search result
 */
public class AvailabilityResult {

	private ResultType result;
	
	private String description;
	
	List<FlightResult> flightResultList;

	public ResultType getResult() {
		return result;
	}
	
	public void setResult(ResultType result) {
		this.result = result;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<FlightResult> getFlightResultList() {
		return flightResultList;
	}
	
	public void setFlightResultList(List<FlightResult> flightResultList) {
		this.flightResultList = flightResultList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((flightResultList == null) ? 0 : flightResultList.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}

	@Override
	public String toString() {
		
		String newLine = "\n";
		String comma = ",";
		String space = " ";
		String euro = "€ ";
		
		if (result.equals(ResultType.KO)){
			return description;
		}
		
		if (result.equals(ResultType.OK)){
			StringBuffer response = new StringBuffer();
			
			for(FlightResult flightResult : flightResultList){
				response.append(flightResult.getFlight()).append(comma).append(space).
					append(getRoundedFloat(flightResult.getTotalAmount())).append(euro);
				response.append(getTravellerPriceListText(flightResult.getTravellerPriceList()));
				response.append(newLine);
			}
			
			return response.toString();
		}
		
		return "AvailabilityResult [result=" + result + ", description=" + description + ", flightResultList=" + flightResultList + "]";
	}
	
	private StringBuffer getTravellerPriceListText(List<TravellerPrice> travellerPriceList){
		
		StringBuffer response = new StringBuffer();
		StringBuffer responseAux = new StringBuffer();	
		String parenthesisOpen = "(";
		String parenthesisClose = ")";
		String plus = " + ";
		
		boolean firstTime = true;		
		
		responseAux.append(parenthesisOpen);
		
		for (TravellerPrice travellerPrice : travellerPriceList){
			
			//Add plus symbol
			if (firstTime){
				firstTime = false;;
			}else{
				responseAux.append(plus);
			}
			responseAux.append(getTravellerPriceText(travellerPrice));
		}
		
		responseAux.append(parenthesisClose);		
		
		response.append(responseAux);
		
		return response;
		
	}
	
	private String getTravellerPriceText(TravellerPrice travellerPrice){
		
		if (travellerPrice != null && travellerPrice.getNumber() > 0){
			StringBuffer response = new StringBuffer();
			String parenthesisOpen = "(";
			String parenthesisClose = ")";
			String space = " ";
			String percent = "%";
			String multiply = "*";
			String of = "of";
			float hundred = 100;
			
			if (travellerPrice.getNumber() > 1){
				response.append(travellerPrice.getNumber()).append(space).append(multiply).append(space).append(parenthesisOpen);
			}
			
			if(travellerPrice.getBreakDownPrice().getPassengerDiscount() > 0){
				response.append(getRoundedFloat(hundred - travellerPrice.getBreakDownPrice().getPassengerDiscount())).
				append(percent).append(space).append(of).append(space).append(parenthesisOpen);
			}
			
			if(travellerPrice.getBreakDownPrice().getDateDiscount() > 0){
				response.append(getRoundedFloat(travellerPrice.getBreakDownPrice().getDateDiscount())).
				append(percent).append(space).append(of).append(space).append(getRoundedFloat(travellerPrice.getBreakDownPrice().getBasePrice()));
			}else{
				response.append(getRoundedFloat(travellerPrice.getBreakDownPrice().getBasePrice()));
			}
			
			if(travellerPrice.getNumber() > 1){
				response.append(parenthesisClose);
			}
			
			if(travellerPrice.getBreakDownPrice().getPassengerDiscount() > 0){
				response.append(parenthesisClose);
			}
			
			return response.toString();
		}		
		
		return ""; 	
	}
	
	class TravellerPriceResponse{
		
		float amount = 0f;
		String description = "";		
		
		public TravellerPriceResponse() {
			super();
		}
		
		public TravellerPriceResponse(float amount, String description) {
			super();
			this.amount = amount;
			this.description = description;
		}
		
		public float getAmount() {
			return amount;
		}
		
		public void setAmount(float amount) {
			this.amount = amount;
		}		
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}	
		
	}
	
	private String getRoundedFloat(float number){
		
		String aux;
		String separator = ",";
		String pattern = "00";
		
		DecimalFormat df = new DecimalFormat();
		
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		
		aux = df.format(number);
		
		String[] parts = aux.split(separator);
		
		if (pattern.equals(parts[1])){
			return parts[0];
		}else{
			return aux;
		}		
	}
	
}
