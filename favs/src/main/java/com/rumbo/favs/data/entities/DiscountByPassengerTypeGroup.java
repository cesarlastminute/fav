package com.rumbo.favs.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class to allow work with object list and marshal
 * 
 */
@XmlRootElement(name = "discountByPassengerTypeGroup")
@XmlAccessorType (XmlAccessType.FIELD)
public class DiscountByPassengerTypeGroup {

	@XmlElement(name = "discountByPassengerType")
	List<DiscountByPassengerType> discountByPassengerTypeGroup = new ArrayList<>();
	
	public DiscountByPassengerTypeGroup() {
		super();
	}

	public DiscountByPassengerTypeGroup(List<DiscountByPassengerType> discountByPassengerTypeGroup) {
		super();
		this.discountByPassengerTypeGroup = discountByPassengerTypeGroup;
	}

	public List<DiscountByPassengerType> getDiscountByPassengerTypeGroup() {
		return discountByPassengerTypeGroup;
	}

	public void setDiscountByPassengerTypeGroup(List<DiscountByPassengerType> discountByPassengerTypeGroup) {
		this.discountByPassengerTypeGroup = discountByPassengerTypeGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discountByPassengerTypeGroup == null) ? 0 : discountByPassengerTypeGroup.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiscountByPassengerTypeGroup other = (DiscountByPassengerTypeGroup) obj;
		if (discountByPassengerTypeGroup == null) {
			if (other.discountByPassengerTypeGroup != null)
				return false;
		} else if (!discountByPassengerTypeGroup.equals(other.discountByPassengerTypeGroup))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DiscountByPassengerTypeGroup [discountByPassengerTypeGroup=" + discountByPassengerTypeGroup + "]";
	}
	
}
