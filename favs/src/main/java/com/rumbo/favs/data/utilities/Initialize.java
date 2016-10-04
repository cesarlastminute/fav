package com.rumbo.favs.data.utilities;


public class Initialize {
		
	public void run(){
		
		loadFiles();		
	}
	
	/**
	 * Write xml files from csv files
	 * 
	 * Get all files from files.Properties
	 * 
	 */
	private void loadFiles(){
		
		ManageProperties manageProperties = new ManageProperties();
		
		String csvFileResourceFolder = manageProperties.getConfigProperty(ManageProperties.CSV_FILE_RESOURCE_FOLDER);
		String xmlFileResourceFolder = manageProperties.getConfigProperty(ManageProperties.XML_FILE_RESOURCE_FOLDER);
		
		if (csvFileResourceFolder != null && !csvFileResourceFolder.isEmpty() && 
				xmlFileResourceFolder != null && !xmlFileResourceFolder.isEmpty()){
			
			String flightsFile = manageProperties.getFilesProperty(ReadCsv.FLIGHTSFILE);			
			if (flightsFile != null && !flightsFile.isEmpty()){
				ReadCsv.csvToXmlFlight(csvFileResourceFolder + flightsFile + ReadCsv.CSVEXTENSION, xmlFileResourceFolder + flightsFile + ReadCsv.XMLEXTENSION);
			}
			
			String infantPricesFile = manageProperties.getFilesProperty(ReadCsv.INFANTPRICESFILE);			
			if (infantPricesFile != null && !infantPricesFile.isEmpty()){
				ReadCsv.csvToXmlInfantPrices(csvFileResourceFolder + infantPricesFile + ReadCsv.CSVEXTENSION, xmlFileResourceFolder + infantPricesFile + ReadCsv.XMLEXTENSION);
			}
			
			String applicationConfigurationFile = manageProperties.getFilesProperty(ReadCsv.APPLICATIONCONFIGURATIONFILE);			
			if (applicationConfigurationFile != null && !applicationConfigurationFile.isEmpty()){
				ReadCsv.csvToXmlApplicationConfigurationByPassengerType(csvFileResourceFolder + applicationConfigurationFile + ReadCsv.CSVEXTENSION, xmlFileResourceFolder + applicationConfigurationFile + ReadCsv.XMLEXTENSION);
			}
			
			String discountByPassengerTypeFile = manageProperties.getFilesProperty(ReadCsv.DISCOUNTBYPASSENGERTYPEFILE);			
			if (discountByPassengerTypeFile != null && !discountByPassengerTypeFile.isEmpty()){
				ReadCsv.csvToXmlDiscountByPassengerType(csvFileResourceFolder + discountByPassengerTypeFile + ReadCsv.CSVEXTENSION, xmlFileResourceFolder + discountByPassengerTypeFile + ReadCsv.XMLEXTENSION);
			}
			
			String daysToDepartureDateFile = manageProperties.getFilesProperty(ReadCsv.DAYSTODEPARTUREDATEFILE);			
			if (daysToDepartureDateFile != null && !daysToDepartureDateFile.isEmpty()){
				ReadCsv.csvToXmlDaysToDepartureDate(csvFileResourceFolder + daysToDepartureDateFile + ReadCsv.CSVEXTENSION, xmlFileResourceFolder + daysToDepartureDateFile + ReadCsv.XMLEXTENSION);
			}
			
			String airportFile = manageProperties.getFilesProperty(ReadCsv.AIRPORTSFILE);			
			if (airportFile != null && !airportFile.isEmpty()){
				ReadCsv.csvToXmlAirport(csvFileResourceFolder + airportFile + ReadCsv.CSVEXTENSION, xmlFileResourceFolder + airportFile + ReadCsv.XMLEXTENSION);
			}
		}		
	}	
	
}
