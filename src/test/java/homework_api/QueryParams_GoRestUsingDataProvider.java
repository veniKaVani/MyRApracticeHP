package homework_api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class QueryParams_GoRestUsingDataProvider {
	
	
	@DataProvider
	public Object[][] getUserData_hashMap() {
		return new Object[][] {
			{"Yogendra Pothuvaal","pothuvaal_yogendra@bauch.test","male","active"},
			{"Navin Joshi","joshi_navin@bednar.example","female", "inactive"},
			{"Giriraj Malik","giriraj_malik@dibbert-little.example","male","active"},
		};
	}
	
	@Test(dataProvider = "getUserData_hashMap")
	public void getApiWithQueryParams(String username,String email, String gender, String status) {
		
		RestAssured.baseURI = "https://gorest.co.in";
	
	
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("name", username);
		queryMap.put("email", email);
		queryMap.put("gender", gender);
		queryMap.put("status", status);
	
	
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
