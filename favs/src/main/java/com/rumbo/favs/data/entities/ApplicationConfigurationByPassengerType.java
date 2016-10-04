package com.rumbo.favs.data.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.rumbo.favs.business.bean.PassengerType;

/**
 * Application configuration
 * by passenger type
 * 
 */
@XmlRootElement(name = "applicationConfigurationByPassengerType")
@XmlAccessorType (XmlAccessType.FIELD)
public class ApplicationConfigurationByPassengerType {

	private String name;
	private String description;
	private String status;
	private String adt;
	private String chd;
	private String inf;
			
	public ApplicationConfigurationByPassengerType() {
		super();
	}

	public ApplicationConfigurationByPassengerType(String name, String description, String status, String adt, String chd, String inf) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
		this.adt = adt;
		this.chd = chd;
		this.inf = inf;
	}
	
	public String getPropertyValuByPassengerTypepassengerType(PassengerType passengerType){
		
		switch(passengerType) {
			
		case ADT:
			return getAdt();
		case CHD:
			return getChd();
		case INF:
			return getInf();
		default:
			return null;
		}
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAdt() {
		return adt;
	}
	
	public void setAdt(String adt) {
		this.adt = adt;
	}
	
	public String getChd() {
		return chd;
	}
	
	public void setChd(String chd) {
		this.chd = chd;
	}
	
	public String getInf() {
		return inf;
	}
	
	public void setInf(String inf) {
		this.inf = inf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adt == null) ? 0 : adt.hashCode());
		result = prime * result + ((chd == null) ? 0 : chd.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((inf == null) ? 0 : inf.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "ApplicationConfigurationByPassengerType [name=" + name + ", description=" + description + ", status=" + status + ", adt=" + adt + ", chd="
				+ chd + ", inf=" + inf + "]";
	}
	
}
