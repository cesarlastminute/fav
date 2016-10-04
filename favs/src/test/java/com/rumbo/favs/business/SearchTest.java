package com.rumbo.favs.business;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Properties;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.rumbo.favs.business.bean.AvailabilityResult;
import com.rumbo.favs.business.bean.FlightResult;
import com.rumbo.favs.business.bean.ResultType;
import com.rumbo.favs.business.bean.exceptions.SearchCriteriaException;
import com.rumbo.favs.business.services.ISearchEngine;
import com.rumbo.favs.business.services.impl.SearchEngineImpl;
import com.rumbo.favs.data.utilities.Initialize;
import com.rumbo.favs.data.utilities.ReadCsv;

public class SearchTest {
	
	Properties prop = new Properties();
	
	String configFile = "config.properties";	
	
	private static ISearchEngine searchEngine;
	
	private String[] getFlights(AvailabilityResult availabilityResult){
				
		String[] flights = new String[0];
		int count = 0;
		
		if (availabilityResult != null && availabilityResult.getFlightResultList() != null){
			flights = new String[availabilityResult.getFlightResultList().size()];
			for(FlightResult flightResult : availabilityResult.getFlightResultList()){
				flights[count] = flightResult.getFlight();
				count++;
			}
		}
		
		return flights;
	}
	
	@BeforeClass
	public static void initialize()
	{
		Initialize init = new Initialize();
		init.run();
	}
	
	@AfterClass
	public static void close()
	{
		ReadCsv.cleanXmlFiles();
	}
	
	@Before
	public void beforeTest()
	{
		searchEngine = new SearchEngineImpl();
	}
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();	
	
	@Test		
	public void test1() {
		System.out.println("TEST 1");
		thrown.expect(SearchCriteriaException.class);
	    thrown.expectMessage(SearchCriteriaException.ERROR_DEPARTURE_CITY);
	   
		AvailabilityResult availabilityResult = searchEngine.search("ALB", "MAD", 45, 1, 1, 1);				
		printResult(availabilityResult);
	}
	
	@Test		
	public void test2() {
		System.out.println("TEST 2");
		thrown.expect(SearchCriteriaException.class);
	    thrown.expectMessage(SearchCriteriaException.ERROR_DESTINATION_CITY);
	   
		AvailabilityResult availabilityResult = searchEngine.search("MAD", "SVQ", 45, 1, 1, 1);				
		printResult(availabilityResult);
	}
	
	@Test		
	public void test3() {
		System.out.println("TEST 3");
		thrown.expect(SearchCriteriaException.class);
	    thrown.expectMessage(SearchCriteriaException.ERROR_DEPARTURE_DATE);
	   
		AvailabilityResult availabilityResult = searchEngine.search("MAD", "BCN", -45, 1, 1, 1);				
		printResult(availabilityResult);
	}
	
	@Test		
	public void test4() {
		System.out.println("TEST 4");
		thrown.expect(SearchCriteriaException.class);
	    thrown.expectMessage(SearchCriteriaException.ERROR_NUM_PASSENGERS);
	   
		AvailabilityResult availabilityResult = searchEngine.search("MAD", "BCN", 45, -1, 1, 1);				
		printResult(availabilityResult);
	}
	
	@Test		
	public void test5() {
		System.out.println("TEST 5");
		thrown.expect(SearchCriteriaException.class);
	    thrown.expectMessage(SearchCriteriaException.ERROR_NUM_PASSENGERS);
	   
		AvailabilityResult availabilityResult = searchEngine.search("MAD", "BCN", 45, 1, -1, 1);				
		printResult(availabilityResult);
	}
	
	@Test		
	public void test6() {
		System.out.println("TEST 6");
		thrown.expect(SearchCriteriaException.class);
	    thrown.expectMessage(SearchCriteriaException.ERROR_NUM_PASSENGERS);
	   
		AvailabilityResult availabilityResult = searchEngine.search("MAD", "BCN", 45, 1, 1, -1);				
		printResult(availabilityResult);
	}
	
	@Test		
	public void test7() {
		System.out.println("TEST 7");	   
		AvailabilityResult availabilityResult = searchEngine.search("CDG", "FRA", 45, 1, 1, 1);
		assertNotNull(availabilityResult);
		assertThat(availabilityResult.getResult(),is(ResultType.KO));
		printResult(availabilityResult);
	}
	
	@Test		
	public void test8() {
		try{
			System.out.println("TEST 8");
			AvailabilityResult availabilityResult = searchEngine.search("CPH", "FCO", 5, 1, 0, 0);
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			assertThat(getFlights(availabilityResult), arrayContainingInAnyOrder("TK4667","U24631"));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}
	
	@Test		
	public void test12() {
		try{
			System.out.println("TEST 12");
			AvailabilityResult availabilityResult = searchEngine.search("AMS", "FRA", 30, 1, 0, 0);		
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}
	
	@Test		
	public void test13() {
		try{
			System.out.println("TEST 13");
			AvailabilityResult availabilityResult = searchEngine.search("LHR", "IST", 15, 2, 1, 1);		
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}
	
	@Test		
	public void test14() {
		try{
			System.out.println("TEST 14");
			AvailabilityResult availabilityResult = searchEngine.search("BCN", "MAD", 2, 1, 2, 0);		
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			printResult(availabilityResult);		
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}
	
	@Test		
	public void test15() {
		try{
			System.out.println("TEST 15");
			AvailabilityResult availabilityResult = searchEngine.search("CPH", "MAD", 45, 2, 2, 2);		
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}
	
	@Test		
	public void test16() {
		try{
			System.out.println("TEST 16");
			AvailabilityResult availabilityResult = searchEngine.search("CPH", "MAD", 45, 0, 1, 0);		
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}	
	}
	
	@Test		
	public void test17() {
		try{
			System.out.println("TEST 17");
			AvailabilityResult availabilityResult = searchEngine.search("CPH", "MAD", 45, 0, 0, 1);		
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}
	
	@Test		
	public void test18() {
		try{
			System.out.println("TEST 18");
			AvailabilityResult availabilityResult = searchEngine.search("CPH", "MAD", 45, 1, 1, 1);		
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}	
	
	private void printResult(AvailabilityResult availabilityResult){		
		if (availabilityResult != null){
			System.out.println(availabilityResult.toString());
		}		
	}
}
