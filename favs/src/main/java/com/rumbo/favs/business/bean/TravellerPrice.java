package com.rumbo.favs.business.bean;

/**
 * Search result
 */
public class TravellerPrice{

	PassengerType type;
	int number;
	float totalAmount;
	BreakDownPrice breakDownPrice;
		
	public TravellerPrice() {
		super();
	}
	
	public TravellerPrice(PassengerType type, int number, BreakDownPrice breakDownPrice) {
		super();
		this.type = type;
		this.number = number;
		this.breakDownPrice = breakDownPrice;
	}
	
	public PassengerType getType() {
		return type;
	}
	
	public void setType(PassengerType type) {
		this.type = type;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public BreakDownPrice getBreakDownPrice() {
		return breakDownPrice;
	}
	
	public void setBreakDownPrice(BreakDownPrice breakDownPrice) {
		this.breakDownPrice = breakDownPrice;
	}	
	
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breakDownPrice == null) ? 0 : breakDownPrice.hashCode());
		result = prime * result + number;
		result = prime * result + Float.floatToIntBits(totalAmount);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "TravellerPrice [type=" + type + ", number=" + number + ", totalAmount=" + totalAmount + ", breakDownPrice=" + breakDownPrice + "]";
	}
		
}
