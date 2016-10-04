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

import com.rumbo.favs.business.bean.PassengerType;
import com.rumbo.favs.data.dao.IDiscountByPassengerTypeDao;
import com.rumbo.favs.data.dao.IInfantPricesDao;
import com.rumbo.favs.data.entities.DiscountByPassengerType;
import com.rumbo.favs.data.utilities.ManageProperties;
import com.rumbo.favs.data.utilities.ReadCsv;

/**
 * Infant Price DAO
 * 
 */
public class DiscountByPassengerTypeDaoImpl implements IDiscountByPassengerTypeDao{

	/**
	 * Get infant price by airline
	 * 
	 * @return InfantPrice
	 */
	public DiscountByPassengerType getDiscountPercent(PassengerType passengerType) {
		
		DiscountByPassengerType discountByPassengerType = new DiscountByPassengerType();
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
				
		try {
			ManageProperties manageProperties = new ManageProperties();
			
			String xmlDiscountByPassengerTypeFile = manageProperties.getFilesProperty(ReadCsv.DISCOUNTBYPASSENGERTYPEFILE);
			String fileResourceFolder = manageProperties.getConfigProperty(ManageProperties.XML_FILE_RESOURCE_FOLDER);
			
			if (fileResourceFolder != null && !fileResourceFolder.isEmpty() &&
					xmlDiscountByPassengerTypeFile != null && !xmlDiscountByPassengerTypeFile.isEmpty()){
			
			    builder = builderFactory.newDocumentBuilder();
			    
				// First time I create xml file, if I try to get it 
				// through ClassLoader, don't get it, so I do this
			    document = builder.parse(new FileInputStream(fileResourceFolder + xmlDiscountByPassengerTypeFile + ReadCsv.XMLEXTENSION));
			    
			    XPath xPath =  XPathFactory.newInstance().newXPath();
				
			    //Query
				String expression = "/discountByPassengerTypeGroup/discountByPassengerType[" + IDiscountByPassengerTypeDao.PASSENGERTYPE + "='"+ passengerType.toString() + "']";		    
				NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
				
				//None result
				if (nodeList.getLength() == 0){
					return null;
				}
							
	            Node nNode = nodeList.item(0);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element element = (Element) nNode;
	               return getDiscountByPassengerType(element);
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
		
		return discountByPassengerType;
	}
	
	/**
	 * Get infantPrice from xml element
	 * 
	 * @return InfantPrice
	 */
	private DiscountByPassengerType getDiscountByPassengerType(Element element){
		
		DiscountByPassengerType discountByPassengerType = null;
		
		if (element != null){
			discountByPassengerType = new DiscountByPassengerType(element.getElementsByTagName(IDiscountByPassengerTypeDao.PASSENGERTYPE).item(0).getTextContent(),
					element.getElementsByTagName(IDiscountByPassengerTypeDao.DISCOUNTPERCENT).item(0).getTextContent());			
		}		
		
		return discountByPassengerType;
	}
}
