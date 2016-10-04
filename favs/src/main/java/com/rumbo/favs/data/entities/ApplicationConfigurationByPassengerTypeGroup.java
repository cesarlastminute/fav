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
@XmlRootElement(name = "applicationConfigurationByPassengerTypeGroup")
@XmlAccessorType (XmlAccessType.FIELD)
public class ApplicationConfigurationByPassengerTypeGroup {

	@XmlElement(name = "applicationConfigurationByPassengerType")
	List<ApplicationConfigurationByPassengerType> applicationConfigurationByPassengerTypeGroup = new ArrayList<>();

	public List<ApplicationConfigurationByPassengerType> getApplicationConfigurationByPassengerTypeGroup() {
		return applicationConfigurationByPassengerTypeGroup;
	}

	public void setApplicationConfigurationByPassengerTypeGroup(List<ApplicationConfigurationByPassengerType> applicationConfigurationByPassengerTypeGroup) {
		this.applicationConfigurationByPassengerTypeGroup = applicationConfigurationByPassengerTypeGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationConfigurationByPassengerTypeGroup == null) ? 0 : applicationConfigurationByPassengerTypeGroup.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "ApplicationConfigurationByPassengerTypeGroup [applicationConfigurationByPassengerTypeGroup=" + applicationConfigurationByPassengerTypeGroup
				+ "]";
	}
	
}
