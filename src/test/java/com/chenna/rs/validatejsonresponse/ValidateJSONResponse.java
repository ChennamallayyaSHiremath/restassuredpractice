package com.chenna.rs.validatejsonresponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class ValidateJSONResponse {


	@Test()
	public void basicJSONResponseValidation() {
		
		given().contentType("application/json")
		
		.when().get("https://reqres.in/api/users?page=2")
		
		.then().statusCode(200)
		.body("data[0].first_name", equalTo("Michael"))
		.body("data[0].last_name", equalTo("Lawson"));

		
	}
	
	@Test(dependsOnMethods = "basicJSONResponseValidation")
	public void responseJSONResponseValidation() {
		
		Response response = given().contentType("application/json")
		
		.when().get("https://reqres.in/api/users?page=2");
		
		ResponseBody body = response.body();
		
		String name = body.jsonPath().get("first_name").toString();
		
		System.out.println("First name is " + name);

		
	}
}