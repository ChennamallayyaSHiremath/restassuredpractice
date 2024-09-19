package com.chenna.rs.interview;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;


public class RestAssuredSample {
	
	@Test()
	public void getUsers() {
		
	when()
	.get("https://reqres.in/api/users?page=2")
	
	.then()
	   .statusCode(200).body("page",equalTo(2)).log().all();
	
	}
	
	static int id;
	
	@Test(dependsOnMethods = "getUsers")
	public void createUser() {
		
		System.out.println("In create user method");
		
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("name", "Viraj");
		hm.put("job", "Hiremath");
		
		id=given().contentType("application/json").body(hm)
		
		.when()
		.post("https://reqres.in/api/users").jsonPath().getInt("id");

	}

	@Test(dependsOnMethods = "createUser")
	public void updateUser() {
		
		System.out.println("In update user method");
		
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("name", "Bhadra");
		hm.put("job", "Hiremath");
		
		given().contentType("application/json").body(hm)
		
		.when()
		.put("https://reqres.in/api/users/" + id)
		
		//System.out.println("Update user id " + id);
		
		.then().statusCode(200).log().all();
		
	}
	@Test(dependsOnMethods = "updateUser")
	public void deleteUser() {
		
		System.out.println("In delete user method");
		System.out.println("Update user id " + id);
		
		//HashMap<String, String> hm=new HashMap<String, String>();
		//hm.put("name", "Bhadra");
		//hm.put("job", "Hiremath");
		
		given().contentType("application/json")
		
		.when()
		.delete("https://reqres.in/api/users/" + id)
		
		
		
		.then().statusCode(204).log().all();
		
	}
}
