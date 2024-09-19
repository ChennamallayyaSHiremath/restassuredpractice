package com.chenna.rs.interview;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CookieValidation {
	
	@Test
	public void getCookie() {
	
		 Response response = given()
		.when().get("https://www.google.com/");
		
		//.then().statusCode(200);)
		
		 Map<String, String> allCookies = response.getCookies();
		 //String key=null;

		 for (String key : allCookies.keySet()) {
			 
			 System.out.println("Key is " + key + "\n" + "Cookie value is "  + response.getCookie(key));
		}
}
	
	@Test(dependsOnMethods = "getCookie")
	public void getHeaders() {
		
		System.out.println("In header method ");
	
		 Response response = given()
		.when().get("https://www.google.com/");
		
		//.then().statusCode(200);
	Headers allHeaders = response.getHeaders();
	
	//List<Header> headers = allHeaders.asList();
	
	for (Header header : allHeaders) {
		
	   System.out.println("Header name is " + header.getName() + " and value is " + header.getValue());
	}	
 }
	
	@Test(dependsOnMethods = "getHeaders")
	public void validateHeaders() {
		
		System.out.println("In validate header method ");
		given()
		.when().get("https://www.google.com/")
		
		.then().statusCode(200)
		.header("Content-Encoding", "gzip")
		.header("Content-Type", "text/html; charset=ISO-8859-1")
		.header("Server", "gws").log().body().assertThat();
	
	
 }
}
