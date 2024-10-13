package triplecode;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetAPIwithQueryAndPathParams2 {

	@Test
	public void getUsersWithQueryParams_TestTC1() {
		RestAssured.baseURI = "https://gorest.co.in";

		given().log().all()
				.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
				.contentType(ContentType.JSON)

				.queryParam("name", "Vaidehi").queryParam("status", "inactive").when().log().all()
				.get("/public/v2/users").then().log().all().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON);

	}

	@Test
	public void getUsersWithQueryParams_UsingHashMapTestTC2() {
		RestAssured.baseURI = "https://gorest.co.in";

		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("name", "Vaidehi");
		queryMap.put("status", "inactive");

		given().log().all()
				.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
				.contentType(ContentType.JSON)

				.queryParams(queryMap).when().log().all().get("/public/v2/users").then().log().all().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON);

	}

	@Test
	public void getUsersWithQueryParams_UsingMapDotOfMethodTestTC3() {
		RestAssured.baseURI = "https://gorest.co.in";

		Map<String, String> queryMap = Map.of("name", "Vaidehi", "status", "inactive");

		given().log().all()
				.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
				.contentType(ContentType.JSON)

				.queryParams(queryMap).when().log().all().get("/public/v2/users").then().log().all().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON);

	}

	@Test
	public void getUserDataUsingPathParamTest_TC4() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		 .contentType(ContentType.JSON)
		  .pathParam("userid", "7463210")
		   .when().log().all()
		   .get("/public/v2/users/{userid}/posts")
		   .then().log().all()
		   .assertThat()
		   .statusCode(200);
		
	}
	@DataProvider
	public  Object[][] getUserData() {
		return new Object[][] {
			{"7463210"},
			{"7463210"},		
		};
	}
	@Test(dataProvider="getUserData")
	public void getUserDataUsingDataProviderTest_TC5(String userid) {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		 .contentType(ContentType.JSON)
		  .pathParam("userid", userid)
		   .when().log().all()
		   .get("/public/v2/users/{userid}/posts")
		   .then().log().all()
		   .assertThat()
		   .statusCode(200);
		
	}

}
