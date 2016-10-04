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

import com.rumbo.favs.data.dao.IDaysToDepartureDateDao;
import com.rumbo.favs.data.dao.IInfantPricesDao;
import com.rumbo.favs.data.entities.DaysToDepartureDate;
import com.rumbo.favs.data.entities.InfantPrice;
import com.rumbo.favs.data.utilities.ManageProperties;
import com.rumbo.favs.data.utilities.ReadCsv;

/**
 * Infant Price DAO
 * 
 */
public class DaysToDepartureDateDaoImpl implements IDaysToDepartureDateDao{

	/**
	 * Get infant price by airline
	 * 
	 * @return InfantPrice
	 */
	public DaysToDepartureDate getDiscountPercent(int days) {
		
		DaysToDepartureDate daysToDepartureDate = new DaysToDepartureDate();
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
				
		try {
			ManageProperties manageProperties = new ManageProperties();
			
			String xmlDaysToDepartureDateFile = manageProperties.getFilesProperty(ReadCsv.DAYSTODEPARTUREDATEFILE);
			String fileResourceFolder = manageProperties.getConfigProperty(ManageProperties.XML_FILE_RESOURCE_FOLDER);
			
			if (fileResourceFolder != null && !fileResourceFolder.isEmpty() &&
					xmlDaysToDepartureDateFile != null && !xmlDaysToDepartureDateFile.isEmpty()){
			
			    builder = builderFactory.newDocumentBuilder();
			    
				// First time I create xml file, if I try to get it 
				// through ClassLoader, don't get it, so I do this
			    document = builder.parse(new FileInputStream(fileResourceFolder + xmlDaysToDepartureDateFile + ReadCsv.XMLEXTENSION));
			    
			    XPath xPath =  XPathFactory.newInstance().newXPath();
				
			    //Query
				//String expression = "/daysToDepartureDateGroup/daysToDepartureDate[" + IDaysToDepartureDateDao.MIN + ">=" + days + " and " + IDaysToDepartureDateDao.MAX + "<=" + days + "]";
				String expression = "/daysToDepartureDateGroup/daysToDepartureDate[" + IDaysToDepartureDateDao.MIN + "<=" + days + " and " + IDaysToDepartureDateDao.MAX + ">=" + days + "]";	
				NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
				
				//None result
				if (nodeList.getLength() == 0){
					return null;
				}
							
	            Node nNode = nodeList.item(0);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element element = (Element) nNode;
	               return getDaysToDepartureDate(element);
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
		
		return daysToDepartureDate;
	}
	
	/**
	 * Get infantPrice from xml element
	 * 
	 * @return InfantPrice
	 */
	private DaysToDepartureDate getDaysToDepartureDate(Element element){
		
		DaysToDepartureDate daysToDepartureDate = null;
		
		if (element != null){
			daysToDepartureDate = new DaysToDepartureDate(element.getElementsByTagName(IDaysToDepartureDateDao.MIN).item(0).getTextContent(),
					element.getElementsByTagName(IDaysToDepartureDateDao.MAX).item(0).getTextContent(),
					element.getElementsByTagName(IDaysToDepartureDateDao.DISCOUNTPERCENT).item(0).getTextContent());			
		}		
		
		return daysToDepartureDate;
	}
}
