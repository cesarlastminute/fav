package com.rumbo.favs.data.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ManageProperties {

	private final String MESSAGES_PROPERTIES = "messages.properties";	
	private final String CONFIG_PROPERTIES = "config.properties";
	private final String FILE_PROPERTIES = "files.properties";
		
	public static final String CSV_FILE_RESOURCE_FOLDER = "csvFileResourceFolder";
	public static final String XML_FILE_RESOURCE_FOLDER = "xmlFileResourceFolder";
	
	/**
	 * Get message from message property properties
	 * 
	 */
	public String getMessageProperty(String property){
		
		return getProperty(property, MESSAGES_PROPERTIES);
	}
	
	/**
	 * Get property from config properties
	 * 
	 */
	public String getConfigProperty(String property){
		
		return getProperty(property, CONFIG_PROPERTIES);
	}
	
	/**
	 * Get property from file properties
	 * 
	 */
	public String getFilesProperty(String property){
		
		return getProperty(property, FILE_PROPERTIES);
	}
	
	/**
	 * Get Properties object from files.properties
	 * 
	 */
	public Properties getCsvFileProperties(){
		
		Properties prop = new Properties();		
		InputStream input = null;
		
		try {
			//Get file from resources folder
			ClassLoader classLoader = getClass().getClassLoader();
			input = new FileInputStream(classLoader.getResource(FILE_PROPERTIES).getFile());

			if (input != null){
				// Load a properties file
				prop.load(input);
				// Get properties value
				return prop;
			}		
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Get a property from file
	 * 
	 * Can't be static method because
	 * getClass() method(Object) isn't static
	 * 
	 */
	private String getProperty(String property, String file){
		
		Properties prop = new Properties();		
		InputStream input = null;
		
		try {
			//Get file from resources folder
			ClassLoader classLoader = getClass().getClassLoader();
			input = new FileInputStream(classLoader.getResource(file).getFile());

			if (input != null){
				// Load a properties file
				prop.load(input);
				// Get properties value
				return prop.getProperty(property);
			}		
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
}
