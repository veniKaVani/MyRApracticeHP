package getapitests2_withqueryparams_withbdd;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchResponseDataConcept {
	
	@Test
	public void getContactsList_Test1_NegativeInvalidToken() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		 .header("Authorization", "Bearer -AbcxyZ6543")
		 .when().log().all()
		 .get("/contacts")
		 .then().log().all()
		 .assertThat()
		 .statusCode(401)
		 .and()
		 .assertThat()
		 .body("error", equalTo("Please authenticate."));
		 
	}
	
	@Test
	public void getContactsList_Test2_NegativeInvalidToken() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		Object errorMesg = given().log().all()
		 .header("Authorization", "Bearer -AbcxyZ6543")
		 .when().log().all()
		 .get("/contacts")
		 .then().log().all()
		 .extract()
		 .path("error");
		
		System.out.println(errorMesg);
		Assert.assertEquals(errorMesg, "Please authenticate."); 
		 
	}

	@Test
	public void getSingleUser_FetchUserData() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response getRes = given().log().all()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		.when().log().all()
		.get("/public/v2/users/7415732");
		
		System.out.println("status code:"+getRes.statusCode());
		System.out.println("status line:"+getRes.statusLine());
		getRes.prettyPrint();
		
		JsonPath js = getRes.jsonPath();
		int userid = js.getInt("id");
		System.out.println("user id:"+userid);
	}
	
	@Test
	public void getSingleUser_FetchFullUserData() {
		
			RestAssured.baseURI = "https://gorest.co.in";
			
			Response getRes = given().log().all()
			.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
			.when().log().all()
			.get("/public/v2/users");
			
			System.out.println("status code:"+getRes.statusCode());
			System.out.println("status line:"+getRes.statusLine());
			getRes.prettyPrint();
			
			JsonPath js = getRes.jsonPath();
			
			List<Object> allIds = js.getList("id");
			System.out.println(allIds);
			
			 List<Object> allNames = js.getList("name");
			System.out.println(allNames);
			
			for(Object e:allIds) {
				System.out.println(e);
			}
	}
	
	@Test
	public void getProduct_FetchNestedData_fakeUserAPI() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response getRes = given()
		.when()
		.get("/products");
		
		System.out.println("status code:"+getRes.statusCode());
		System.out.println("status line:"+getRes.statusLine());
		
		JsonPath js = getRes.jsonPath();
		List<Object> priceList = js.getList("price");
		System.out.println(priceList);
		
		List<Object> rateList = js.getList("rating.rate");
		System.out.println(rateList);
		
		List<Object> countList = js.getList("rating.count");
		System.out.println(countList);
		
		List<Object> allIds = js.getList("id");
		System.out.println(allIds);
		
		for(Object id:allIds) {
			System.out.println(id);
		}
		
		
	}
}
