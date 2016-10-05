package com.rumbo.favs.data.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.rumbo.favs.data.dao.IFlightDao;
import com.rumbo.favs.data.entities.Flight;
import com.rumbo.favs.data.entities.FlightGroup;
import com.rumbo.favs.data.utilities.ManageProperties;
import com.rumbo.favs.data.utilities.ReadCsv;

/**
 * Flights (Itinerary) DAO
 * 
 */
public class FlightDaoImpl implements IFlightDao {

	/**
	 * Get available flights from origin and destination
	 * 
	 * @return Flights
	 */
	public FlightGroup getFlightsByItinerary(String origin, String destination) {
		
		FlightGroup flights = new FlightGroup();
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
				
		try {
			ManageProperties manageProperties = new ManageProperties();
			
			String xmlFlightsFile = manageProperties.getFilesProperty(ReadCsv.FLIGHTSFILE);
			String fileResourceFolder = manageProperties.getConfigProperty(ManageProperties.XML_FILE_RESOURCE_FOLDER);
			
			if (fileResourceFolder != null && !fileResourceFolder.isEmpty() &&
					xmlFlightsFile != null && !xmlFlightsFile.isEmpty()){
				
				builder = builderFactory.newDocumentBuilder();
			    
				// First time I create xml file, if I try to get it 
				// through ClassLoader, don't get it, so I do this
			    document = builder.parse(new FileInputStream(fileResourceFolder + xmlFlightsFile + ReadCsv.XMLEXTENSION));
			    
			    XPath xPath =  XPathFactory.newInstance().newXPath();
				
			    //Query
				String expression = "/flightGroup/flight[" + IFlightDao.ORIGIN + "='" + origin + "' and " + IFlightDao.DESTINATION + "='" + destination  + "']";		    
				NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
				
				//None result
				if (nodeList.getLength() == 0){
					return null;
				}
				
				//Iterate query result
				for (int i = 0; i < nodeList.getLength(); i++) {
		            Node nNode = nodeList.item(i);
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		               Element element = (Element) nNode;
		               Flight flight = getFlight(element);
		               if (flight != null){
		            	   flights.getFlightGroup().add(flight);
		               }
		            }
		         }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();			
		} catch (ParserConfigurationException e) {
		    e.printStackTrace();  
		} catch (SAXException e) {
		    e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flights;
	}
	
	/**
	 * Get flight from xml element
	 * 
	 * @return Flights
	 */
	private Flight getFlight(Element element){
		
		Flight flight = null;
		
		if (element != null){
			flight = new Flight(element.getElementsByTagName(IFlightDao.ORIGIN).item(0).getTextContent(),
					element.getElementsByTagName(IFlightDao.DESTINATION).item(0).getTextContent(),
					element.getElementsByTagName(IFlightDao.AIRLINE).item(0).getTextContent(),
					element.getElementsByTagName(IFlightDao.BASEPRICE).item(0).getTextContent());			
		}		
		
		return flight;
	}
}
