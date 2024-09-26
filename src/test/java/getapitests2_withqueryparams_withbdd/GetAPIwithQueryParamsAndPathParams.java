package getapitests2_withqueryparams_withbdd;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetAPIwithQueryParamsAndPathParams {
	
	@Test
	public void getUserWith_QueryParamsTest1() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		 .header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		 .queryParam("name", "Nanda")
		 .queryParam("gender", "male")
		 .when().log().all()
		 .get("/public/v2/users")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200)
		 .and()
		 .contentType(ContentType.JSON);
		 
	}
	
	@Test
	public void getUserWith_QueryParamsTest2_WithHashMap() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//using HashMap to store query params and use them as an object to supply
//		Map<String, String> queryMap = new HashMap<String, String>();
//		queryMap.put("name", "Nanda");
//		queryMap.put("gender", "male");
//		queryMap.put("status", "active");
		
		//using Map.of() --which optimizes the code and does the same task:IMMUTABLE HASHMAP
		Map<String, String> queryMap = Map.of(
			                               "name", "Nanda",	
		                                   "gender", "male",
		                                    "status","active"
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
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"7415744"},
			{"7415742"},
			{"7415739"},
		};
	}
	
	
	@Test(dataProvider = "getUserData")
	public void getUserData_UsingPathParamTest3(String userid) {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		.pathParam("userid", userid)
		.when().log().all()
		.get("public/v2/users/{userid}/posts")
		.then().log().all()
		.assertThat()
		.statusCode(200);
		
	}

}
