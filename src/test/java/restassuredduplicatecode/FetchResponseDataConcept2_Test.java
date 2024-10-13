package restassuredduplicatecode;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchResponseDataConcept2_Test {
	
	@Test
	public void getContactsAPITest1_withValidToken() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given()
		.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmE4YWM4NjFiMjA2NTAwMTM0MmRlZTkiLCJpYXQiOjE3Mjg1MTQ5MTF9.G2YqTPnkgbeirHYOQdnL4UphpaKiISWDr6ZQlVTTlQQ")
		.when()
		.get("/contacts")
		.then()
		.assertThat()
		.statusCode(200);
		
		
	}
	
	@Test
	public void getContactsAPITest2_withInValidToken() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given()
		.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmE4YWM4NjFiMjA2NTAwMTM0MmRlZTkiLCJpYXQiOjE3Mjg1MBinkaInvalidToken")
		.when()
		.get("/contacts")
		.then()
		.assertThat()
		.statusCode(401)
		.assertThat()
		.body("error", equalTo("Please authenticate."));
		
		
	}
	
	@Test
	public void getContactsAPITest3_withInValidToken() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		String errorMesg = given()
		.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmE4YWM4NjFiMjA2NTAwMTM0MmRlZTkiLCJpYXQiOjE3Mjg1MBinkaInvalidToken")
		.when()
		.get("/contacts")
		.then()
		.extract()
		.path("error");
		
		System.out.println(errorMesg);
		Assert.assertEquals(errorMesg, "Please authenticate.");
		
		
	}
	
	@Test
	public void getGoRestSingleUserDataTest4_withPathParam() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response getRes = given().log().all()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		.when().log().all()
		.get("/public/v2/users/7463205");
		
		System.out.println(getRes.statusCode());
		System.out.println(getRes.statusLine());
		getRes.prettyPrint();
		
		JsonPath js = getRes.jsonPath();
		int userId = js.getInt("id");
		System.out.println("user id is:"+userId);
		
		String userName = js.getString("name");
		System.out.println("user name is: "+userName);
		
		String userEmail = js.getString("email");
		System.out.println("user email is:"+userEmail);
	}
  
	@Test
	public void getGoRestSingleUserTest5_FetchFullUserData() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response getRes = given().log().all()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		.when().log().all()
		.get("/public/v2/users/7463205");
		
		System.out.println(getRes.statusCode());
		System.out.println(getRes.statusLine());
		getRes.prettyPrint();
		
		JsonPath jsp = getRes.jsonPath();
		List<Object> ids = jsp.getList("id");
		System.out.println(ids);
		
		List<String> names = jsp.getList("name");
		System.out.println(names);
		
//		for(Object e:ids) {
//			System.out.println(e);
//		}
	
	}
	
	@Test
	public void getFakeStoreProducts6_FetchNestedData() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response getRes = given()
							.when()
							.get("/products");
		
		JsonPath jsp = getRes.jsonPath();
		List<Integer> ids = jsp.getList("id");
		System.out.println(ids);
		
		List<Object> prices = jsp.getList("price");
		System.out.println(prices);
		
		List<Object> rates = jsp.getList("rating.rate");
		System.out.println(rates);
		
		List<Integer> countList = jsp.getList("rating.count");
		System.out.println(countList);
		
		for(int i=0;i<ids.size();i++) {
			int id = ids.get(i);
			Object price = prices.get(i);
			Object rate = rates.get(i);
			int count = countList.get(i);
			
	System.out.println("ID: "+id+" price:"+price+" rate:"+" count:"+count);		
			
			
		}
	}
}
