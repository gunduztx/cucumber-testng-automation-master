package com.app.tests;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.poi.ss.formula.functions.Sumproduct;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.beans.Country;
import com.app.beans.Country1;
import com.app.beans.CountryResponse;
import com.app.beans.CountryResponse1;
import com.app.beans.Region;
import com.app.beans.RegionReponse;
import com.app.utilities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiDay4POSTRequests {
	
	/*
	POST SCENARIO:

		given content type is Json
		And Accept type is Json
		When I send POST request to 
		http://34.223.219.142:1212/ords/hr/regions
		with request body :
		{
			"region_id" : 5,
			"region_name" : "murodil's region"
		}
		Then status code should be 201
		And response body should match request body
	*/
	
	
	@Test
	public void postNewRegion() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions/";
// 		String requestJson = "{\"region_id\" : 87721,\"region_name\" : \"Emin's region\" }";
	
		Map requestMap = new HashMap<>();
		requestMap.put("region_id", 836525612);
		requestMap.put("region_name", "Tyson's Region");
		
		
		Response response = given().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON).and().body(requestMap).post(url);
		
		System.out.println(response.statusLine());
		response.prettyPrint();
		
		assertEquals(response.statusCode(), 201);
		
		Map responseMap = response.body().as(Map.class);
		
		assertEquals(responseMap.get("region_id"), requestMap.get("region_id") );
		assertEquals(responseMap.get("region_name"), requestMap.get("region_name"));
		
		
		//String requestJson = "{\"region_id\" : 5,\"region_name\" : \"murodil's region\"}";
		
//		Map requestMap = new HashMap<>();
//		requestMap.put("region_id", 557855);
//		requestMap.put("region_name", "murodil's region");
//		
//		Response response = given().accept(ContentType.JSON)
//							.and().contentType(ContentType.JSON)
//							.and().body(requestMap)
//							.when().post(url);
//		
//		System.out.println(response.statusLine());
//		response.prettyPrint();
//		
//		//Then status code should be 201
//		//And response body should match request body
//		assertEquals(response.statusCode(),201);
//		Map responseMap = response.body().as(Map.class);
//		
//		//assertEquals(responseMap,requestMap); did not work
//		assertEquals(responseMap.get("region_id"),requestMap.get("region_id"));
//		assertEquals(responseMap.get("region_name"),requestMap.get("region_name"));
		
	}
	
	@Test
	public void postUsingPOJO() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions/";
		
		Region region = new Region();
		region.setRegion_id(new Random().nextInt(999999));
		region.setRegion_name("murodil's region");
		
		Response response = given().log().all()
							.accept(ContentType.JSON)
						   .and().contentType(ContentType.JSON)
						   .and().body(region)
						   .when().post(url);
	
		assertEquals(response.statusCode(),201);
		
		RegionReponse responseRegion = response.body().as(RegionReponse.class);
		
		//And response body should match request body
		//region id and region name must match
		assertEquals(responseRegion.getRegion_id(),region.getRegion_id());
		assertEquals(responseRegion.getRegion_name(),region.getRegion_name());
		
	}
	
	@Test
	public void postCountryUsingPojo() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/countries/";
		
		Country reqCountry = new Country();
		reqCountry.setCountry_id("TE");
		reqCountry.setCountry_name("A country");
		reqCountry.setRegion_id(4);
		
		Response response = given().log().all()
				.accept(ContentType.JSON)
			   .and().contentType(ContentType.JSON)
			   .and().body(reqCountry)
			   .when().post(url);
		
		assertEquals(response.getStatusCode(),201);
		
		CountryResponse resCountry = response.body().as(CountryResponse.class);
		
		assertEquals(resCountry.getCountry_id(),reqCountry.getCountry_id()); 
		assertEquals(resCountry.getCountry_name(),reqCountry.getCountry_name()); 
		assertEquals(resCountry.getRegion_id(),reqCountry.getRegion_id());

	}
	
	@Test
	public void Countries() {
		
		String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/countries/";
		
		Map request = new HashMap<>();
		request.put("country_id", "VM");
		request.put("country_name", "Mehmet's country");
		request.put("region_id", 165);
		
		Response response = given().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON).and().body(request)
		.post(url);
		
		System.out.println(response.getStatusLine());
		response.prettyPrint();
		assertEquals(response.statusCode(), 201);
		
		Map responseMap = response.body().as(Map.class);
		
		assertEquals(responseMap.get("country_id"), request.get("country_id"));
		assertEquals(responseMap.get("country_name"), request.get("country_name"));
		assertEquals(responseMap.get("region_id"), request.get("region_id"));
		
		
	}
	@Test
	public void countriesPojo() {
		
		String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/countries/";
		
		Country1 country = new Country1();
		country.setCountry_id("MN");
		country.setCountry_name("Emin's country");
		country.setRegion_id(1324);
		
		Response response = given().log().all().accept(ContentType.JSON).and().contentType(ContentType.JSON)
		.and().body(country).post(url);
		
		System.out.println(response.getStatusLine());
		response.prettyPrint();
		assertEquals(response.statusCode(), 201);
		
		CountryResponse1 countryResponse = response.body().as(CountryResponse1.class);
		
		assertEquals(country.getCountry_id(), countryResponse.getCountry_id());
		assertEquals(country.getCountry_name(), countryResponse.getCountry_name());
		assertEquals(country.getRegion_id(), countryResponse.getRegion_id());
		
		
		
	}
	
	
	
}
