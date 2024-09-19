package com.chenna.rs.interview;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

// Different ways to create request body.

public class DifferentWaysToCreateRequestBody {

	
	public static int userId;
	@Test
	public void createUserHashMap() {
		
		HashMap<String, String> dataJson=new HashMap<String, String>();
		dataJson.put("name", "Chenna");
		dataJson.put("job", "Software Engineer");
		
		given().contentType("application/json").body(dataJson)
		.when().post("https://reqres.in/api/users")
		.then().statusCode(201).log().all();
		
		System.out.println("ID Value is " + userId);
		
	}
	
	@Test(dependsOnMethods = "createUserHashMap")
	public void createUserUsingJSONObject() {
		
		JSONObject dataJson2=new JSONObject();
		
		//HashMap<String, String> dataJson=new HashMap<String, String>();
		dataJson2.put("name", "Chenna2");
		dataJson2.put("job", "Software Engineer2");
		
		given().contentType("application/json").body(dataJson2.toString())
		.when().post("https://reqres.in/api/users")
		.then().statusCode(201).body("name", equalTo("Chenna2")).
		body("job", equalTo("Software Engineer2"));

	}
	@Test(dependsOnMethods = "createUserUsingJSONObject")
	public void createUserUsingPOJOClass() {
		
		System.out.println("In POJO Class");
		
		//JSONObject dataJson2=new JSONObject();
		SamplePOJOClass dataJson2=new SamplePOJOClass();
		
		
		//HashMap<String, String> dataJson=new HashMap<String, String>();
		dataJson2.setName("Chenna POJO");
		dataJson2.setJob("Software Engineer POJO");
		
		given().contentType("application/json").body(dataJson2)
		.when().post("https://reqres.in/api/users")
		.then().statusCode(201).body("name", equalTo("Chenna POJO")).
		body("job", equalTo("Software Engineer POJO"));
		
	}
	
	@Test(dependsOnMethods = "createUserUsingPOJOClass")
	public void createUserUsingJSONFile() throws FileNotFoundException {
		
		System.out.println("In JSON File Method");
		String filepath="src/test/resources/jsonschemas/users.json";
		
		File file=new File(filepath);
	
		FileReader fr=new FileReader(file);
				
		JSONTokener jsntknr=new JSONTokener(fr);
		JSONObject dataJson2=new JSONObject(jsntknr);
		
		given().contentType("application/json").body(dataJson2.toString())
		.when().post("https://reqres.in/api/users")
		.then().statusCode(201).body("name", equalTo("Michael")).
		body("job", equalTo("Lawson"));
	
	}
}
