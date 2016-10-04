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
@XmlRootElement(name = "infantPriceGroup")
@XmlAccessorType (XmlAccessType.FIELD)
public class InfantPriceGroup {

	@XmlElement(name = "infantPrice")
	List<InfantPrice> infantPriceGroup = new ArrayList<>();

	public List<InfantPrice> getInfantPrice() {
		return infantPriceGroup;
	}

	public void setInfantPriceGroup(List<InfantPrice> infantPriceGroup) {
		this.infantPriceGroup = infantPriceGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((infantPriceGroup == null) ? 0 : infantPriceGroup.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "InfantPrice [infantPriceGroup=" + infantPriceGroup + "]";
	}
	
}
