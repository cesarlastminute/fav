package com.rumbo.favs.data.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.rumbo.favs.data.entities.Airport;
import com.rumbo.favs.data.entities.AirportGroup;
import com.rumbo.favs.data.entities.ApplicationConfigurationByPassengerType;
import com.rumbo.favs.data.entities.ApplicationConfigurationByPassengerTypeGroup;
import com.rumbo.favs.data.entities.DaysToDepartureDate;
import com.rumbo.favs.data.entities.DaysToDepartureDateGroup;
import com.rumbo.favs.data.entities.DiscountByPassengerType;
import com.rumbo.favs.data.entities.DiscountByPassengerTypeGroup;
import com.rumbo.favs.data.entities.Flight;
import com.rumbo.favs.data.entities.FlightGroup;
import com.rumbo.favs.data.entities.InfantPrice;
import com.rumbo.favs.data.entities.InfantPriceGroup;

public class ReadCsv {
		
	public static final String CSVEXTENSION = ".csv";
	public static final String XMLEXTENSION = ".xml";
	public static final String FLIGHTSFILE = "flightsFile";
	public static final String INFANTPRICESFILE = "infantPricesFile";
	public static final String APPLICATIONCONFIGURATIONFILE = "applicationConfigurationFile";
	public static final String DISCOUNTBYPASSENGERTYPEFILE = "discountByPassengerTypeFile";
	public static final String DAYSTODEPARTUREDATEFILE = "daysToDepartureDateFile";
	public static final String AIRPORTSFILE = "airportsFile";
			
	public static void csvToXmlFlight(String csvFile, String xmlFile) {
		
		if (csvFile != null && !csvFile.isEmpty() &&
				xmlFile != null && !xmlFile.isEmpty()){
			
			BufferedReader br = null;
			String line = "";
			String splitBy = ",";
			FlightGroup flights = new FlightGroup();

			// Read csv file
			try {				
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					String[] csvFlight = line.split(splitBy);
					Flight flight = new Flight(csvFlight[0],csvFlight[1],csvFlight[2],csvFlight[3]);
					flights.getFlightGroup().add(flight);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Write xml file with JAXB
			try {
				File file = new File(xmlFile);
				JAXBContext jaxbContext = JAXBContext.newInstance(FlightGroup.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(flights, file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void csvToXmlInfantPrices(String csvFile, String xmlFile) {
		
		if (csvFile != null && !csvFile.isEmpty() &&
				xmlFile != null && !xmlFile.isEmpty()){
			
			BufferedReader br = null;
			String line = "";
			String splitBy = ",";
			InfantPriceGroup infantPriceGroup = new InfantPriceGroup();

			// Read csv file
			try {				
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					String[] csvInfantPrice = line.split(splitBy);
					InfantPrice infantPrice = new InfantPrice(csvInfantPrice[0],csvInfantPrice[1],csvInfantPrice[2]);
					infantPriceGroup.getInfantPrice().add(infantPrice);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Write xml file with JAXB
			try {
				File file = new File(xmlFile);
				JAXBContext jaxbContext = JAXBContext.newInstance(InfantPriceGroup.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(infantPriceGroup, file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void csvToXmlDiscountByPassengerType(String csvFile, String xmlFile) {
		
		if (csvFile != null && !csvFile.isEmpty() &&
				xmlFile != null && !xmlFile.isEmpty()){
			
			BufferedReader br = null;
			String line = "";
			String splitBy = ",";
			DiscountByPassengerTypeGroup discountByPassengerTypeGroup = new DiscountByPassengerTypeGroup();

			// Read csv file
			try {				
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					String[] csvDiscountByPassengerType = line.split(splitBy);
					DiscountByPassengerType discountByPassengerType = 
							new DiscountByPassengerType(csvDiscountByPassengerType[0],csvDiscountByPassengerType[1]);
					discountByPassengerTypeGroup.getDiscountByPassengerTypeGroup().add(discountByPassengerType);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Write xml file with JAXB
			try {
				File file = new File(xmlFile);
				JAXBContext jaxbContext = JAXBContext.newInstance(DiscountByPassengerTypeGroup.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(discountByPassengerTypeGroup, file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void csvToXmlDaysToDepartureDate(String csvFile, String xmlFile) {
		
		if (csvFile != null && !csvFile.isEmpty() &&
				xmlFile != null && !xmlFile.isEmpty()){
			
			BufferedReader br = null;
			String line = "";
			String splitBy = ",";
			DaysToDepartureDateGroup daysToDepartureDateGroup = new DaysToDepartureDateGroup();

			// Read csv file
			try {				
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					String[] csvDaysToDepartureDate = line.split(splitBy);
					DaysToDepartureDate daysToDepartureDate = 
							new DaysToDepartureDate(csvDaysToDepartureDate[0],csvDaysToDepartureDate[1],csvDaysToDepartureDate[2]);
					daysToDepartureDateGroup.getDaysToDepartureDateGroup().add(daysToDepartureDate);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Write xml file with JAXB
			try {
				File file = new File(xmlFile);
				JAXBContext jaxbContext = JAXBContext.newInstance(DaysToDepartureDateGroup.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(daysToDepartureDateGroup, file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void csvToXmlApplicationConfigurationByPassengerType(String csvFile, String xmlFile) {
		
		if (csvFile != null && !csvFile.isEmpty() &&
				xmlFile != null && !xmlFile.isEmpty()){
			
			BufferedReader br = null;
			String line = "";
			String splitBy = ",";
			ApplicationConfigurationByPassengerTypeGroup applicationConfigurationByPassengerTypeGroup = new ApplicationConfigurationByPassengerTypeGroup();

			// Read csv file
			try {				
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					String[] csvApplicationConfigurationByPassengerType = line.split(splitBy);
					ApplicationConfigurationByPassengerType applicationConfigurationByPassengerType = 
							new ApplicationConfigurationByPassengerType(csvApplicationConfigurationByPassengerType[0],csvApplicationConfigurationByPassengerType[1],csvApplicationConfigurationByPassengerType[2],
									csvApplicationConfigurationByPassengerType[3],csvApplicationConfigurationByPassengerType[4],csvApplicationConfigurationByPassengerType[5]);
					applicationConfigurationByPassengerTypeGroup.getApplicationConfigurationByPassengerTypeGroup().add(applicationConfigurationByPassengerType);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Write xml file with JAXB
			try {
				File file = new File(xmlFile);
				JAXBContext jaxbContext = JAXBContext.newInstance(ApplicationConfigurationByPassengerTypeGroup.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(applicationConfigurationByPassengerTypeGroup, file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void csvToXmlAirport(String csvFile, String xmlFile) {
		
		if (csvFile != null && !csvFile.isEmpty() &&
				xmlFile != null && !xmlFile.isEmpty()){
			
			BufferedReader br = null;
			String line = "";
			String splitBy = ",";
			AirportGroup airportGroup = new AirportGroup();

			// Read csv file
			try {				
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					String[] csvAirport = line.split(splitBy);
					Airport airport = 
							new Airport(csvAirport[0],csvAirport[1]);
					airportGroup.getAirportGroup().add(airport);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Write xml file with JAXB
			try {
				File file = new File(xmlFile);
				JAXBContext jaxbContext = JAXBContext.newInstance(AirportGroup.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(airportGroup, file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Write xml files from csv files
	 * 
	 * Get all files from files.Properties
	 * 
	 */
	public static void loadFiles(){
		
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
	
	public static void cleanXmlFiles(){
		
		try {
			ManageProperties manageProperties = new ManageProperties();
			String fileResourceFolder = manageProperties.getConfigProperty(ManageProperties.XML_FILE_RESOURCE_FOLDER);
			
			if (fileResourceFolder != null && !fileResourceFolder.isEmpty()){
				File dir = new File(fileResourceFolder);
				
				for(File file: dir.listFiles()){
					 if (!file.isDirectory()) 
					        file.delete();
				}			
			}  
		} catch (SecurityException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    System.err.println(e);
		}		
	}
}