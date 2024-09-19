package com.chenna.rs.interview;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
public class PathAndQueryParams {
	
	@Test
	public void pathAndQueryParam() {
		
		given()
			.pathParam("mypathParam", "users")
			//.queryParam("page", 2)
			//.queryParam("id", 5)
		
		.when().get("https://reqres.in/api/{mypathParam}")
		
		.then().statusCode(200).log().all();
		
		
		
	}

}
