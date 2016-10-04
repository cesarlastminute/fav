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

import com.rumbo.favs.data.dao.IAirportDao;
import com.rumbo.favs.data.entities.Airport;
import com.rumbo.favs.data.utilities.ManageProperties;
import com.rumbo.favs.data.utilities.ReadCsv;

/**
 * Infant Price DAO
 * 
 */
public class AirportDaoImpl implements IAirportDao{

	/**
	 * Get infant price by airline
	 * 
	 * @return InfantPrice
	 */
	public Airport getAirportByIataCode(String iataCode) {
		
		Airport airport = new Airport();
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
				
		try {
			ManageProperties manageProperties = new ManageProperties();
			
			String xmlAirportsFile = manageProperties.getFilesProperty(ReadCsv.AIRPORTSFILE);
			String fileResourceFolder = manageProperties.getConfigProperty(ManageProperties.XML_FILE_RESOURCE_FOLDER);
			
			if (fileResourceFolder != null && !fileResourceFolder.isEmpty() &&
					xmlAirportsFile != null && !xmlAirportsFile.isEmpty()){
			
			    builder = builderFactory.newDocumentBuilder();
			    
				// First time I create xml file, if I try to get it 
				// through ClassLoader, don't get it, so I do this
			    document = builder.parse(new FileInputStream(fileResourceFolder + xmlAirportsFile + ReadCsv.XMLEXTENSION));
			    
			    XPath xPath =  XPathFactory.newInstance().newXPath();
				
			    //Query
				String expression = "/airportGroup/airport[" + IAirportDao.IATACODE + "='"+ iataCode + "']";		    
				NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
				
				//None result
				if (nodeList.getLength() == 0){
					return null;
				}
							
		        Node nNode = nodeList.item(0);
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		           Element element = (Element) nNode;
		           return getAirport(element);
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
		
		return airport;
	}
	
	/**
	 * Get infantPrice from xml element
	 * 
	 * @return InfantPrice
	 */
	private Airport getAirport(Element element){
		
		Airport airport = null;
		
		if (element != null){
			airport = new Airport(element.getElementsByTagName(IAirportDao.IATACODE).item(0).getTextContent(),
					element.getElementsByTagName(IAirportDao.CITY).item(0).getTextContent());			
		}		
		
		return airport;
	}
}
