package com.rumbo.favs.business;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.rumbo.favs.business.bean.AvailabilityResult;
import com.rumbo.favs.business.bean.FlightResult;
import com.rumbo.favs.business.bean.ResultType;
import com.rumbo.favs.business.bean.exceptions.SearchCriteriaException;
import com.rumbo.favs.business.services.ISearchEngine;
import com.rumbo.favs.business.services.impl.SearchEngineImpl;
import com.rumbo.favs.data.utilities.MyMath;
import com.rumbo.favs.data.utilities.ReadCsv;

public class SearchTest {
	
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
	
	private String[] getTotalAmountByFlight(AvailabilityResult availabilityResult){
		
		String[] totalAmount = new String[0];
		int count = 0;
		
		if (availabilityResult != null && availabilityResult.getFlightResultList() != null){
			totalAmount = new String[availabilityResult.getFlightResultList().size()];
			for(FlightResult flightResult : availabilityResult.getFlightResultList()){
				totalAmount[count] = MyMath.getRoundedFloat(flightResult.getTotalAmount());
				count++;
			}
		}
		
		return totalAmount;
	}
	
	@BeforeClass
	public static void initialize()
	{
		ReadCsv.loadFiles();
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
		thrown.expect(SearchCriteriaException.class);
	    thrown.expectMessage(SearchCriteriaException.ERROR_NONE_PASSENGERS);
	   
		AvailabilityResult availabilityResult = searchEngine.search("MAD", "BCN", 45, 0, 0, 0);				
		printResult(availabilityResult);
	}
	
	@Test		
	public void test8() {
		System.out.println("TEST 8");	   
		AvailabilityResult availabilityResult = searchEngine.search("CDG", "FRA", 45, 1, 1, 1);
		assertNotNull(availabilityResult);
		assertThat(availabilityResult.getResult(),is(ResultType.KO));
		printResult(availabilityResult);
	}
	
	@Test		
	public void test9() {
		try{
			System.out.println("TEST9");
			AvailabilityResult availabilityResult = searchEngine.search("AMS", "FRA", 30, 1, 0, 0);		
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			assertThat(getFlights(availabilityResult), arrayContainingInAnyOrder("TK2372","TK2659","LH5909"));
			assertThat(getTotalAmountByFlight(availabilityResult), arrayContainingInAnyOrder("157,60","198,40","90,40"));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}
	
	@Test		
	public void test10() {
		try{
			System.out.println("TEST 10");
			AvailabilityResult availabilityResult = searchEngine.search("LHR", "IST", 15, 2, 1, 1);		
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			assertThat(getFlights(availabilityResult), arrayContainingInAnyOrder("TK8891","LH1085"));
			assertThat(getTotalAmountByFlight(availabilityResult), arrayContainingInAnyOrder("806","481,19"));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}
	
	@Test		
	public void test11() {
		try{
			System.out.println("TEST 11");
			AvailabilityResult availabilityResult = searchEngine.search("BCN", "MAD", 2, 1, 2, 0);		
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			assertThat(getFlights(availabilityResult), arrayContainingInAnyOrder("IB2171","LH5496"));
			assertThat(getTotalAmountByFlight(availabilityResult), arrayContainingInAnyOrder("909,09","1.028,43"));
			printResult(availabilityResult);		
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}
	
	@Test		
	public void test12() {
		try{
			System.out.println("TEST 12");
			AvailabilityResult availabilityResult = searchEngine.search("CPH", "FCO", 0, 1, 0, 0);
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			assertThat(getFlights(availabilityResult), arrayContainingInAnyOrder("TK4667","U24631"));
			assertThat(getTotalAmountByFlight(availabilityResult), arrayContainingInAnyOrder("205,50","402"));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}	
	
	@Test		
	public void test13() {
		try{
			System.out.println("TEST 13");
			AvailabilityResult availabilityResult = searchEngine.search("CDG", "CPH", 17, 3, 2, 2);
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			assertThat(getFlights(availabilityResult), arrayContainingInAnyOrder("LH5778","IB5257","TK2044"));
			assertThat(getTotalAmountByFlight(availabilityResult), arrayContainingInAnyOrder("1.155,42","1.317,66","817,24"));
			printResult(availabilityResult);	
		}catch (SearchCriteriaException e){
			e.printStackTrace();
		}
	}
	
	@Test		
	public void test14() {
		try{
			System.out.println("TEST 14");
			AvailabilityResult availabilityResult = searchEngine.search("FRA", "LHR", 60, 1, 1, 1);
			assertNotNull(availabilityResult);
			assertThat(availabilityResult.getResult(),is(ResultType.OK));
			assertThat(getFlights(availabilityResult), arrayContainingInAnyOrder("BA8162","IB9443","TK3167"));
			assertThat(getTotalAmountByFlight(availabilityResult), arrayContainingInAnyOrder("288,88","349,34","162,65"));
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
