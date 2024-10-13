package restassuredduplicatecode;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetGoRestUserWithQueryAndPath2_Params {
	
	@Test
	public void getUserWithQueryParams_1() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
	//	   .header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		   .queryParam("name", "Kalinda")
		   .queryParam("status", "active")
		   .when().log().all()
		   .get("/public/v2/users")
		   .then().log().all()
		   .assertThat()
		   .statusCode(200)
		   .and()
		   .contentType(ContentType.JSON);
		  
		
	}
	
	@Test
	public void getUserDetails_UsingHashMapTest_2() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
//		Map<String, String> queryMap = new HashMap<String, String>();
//		queryMap.put("name", "Kalinda");
//		queryMap.put("status", "active");
//		queryMap.put("gender", "male");
		
		Map<String, String> queryMap = Map.of(
				"name", "Kalinda",
				"status", "active",
				"gender", "male"
				);
		
		
		given().log().all()
			   .header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
			   .queryParams(queryMap)
			   .when().log().all()
			   .get("/public/v2/users")
			   .then().log().all()
			   .assertThat()
			   .statusCode(200)
			   .and()
			   .contentType(ContentType.JSON);
			  
			
		
	}
	
	
	

}
