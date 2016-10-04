package com.rumbo.favs.data.dao;

import com.rumbo.favs.business.bean.ApplicationConfigurationType;
import com.rumbo.favs.data.entities.ApplicationConfigurationByPassengerType;

public interface IApplicationConfigurationByPassengerTypeDao {

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String STATUS = "status";
	public static final String ADT = "adt";
	public static final String CHD = "chd";
	public static final String INF = "inf";
	
	public ApplicationConfigurationByPassengerType getApplicationConfigurationByName(ApplicationConfigurationType property);
	
}
