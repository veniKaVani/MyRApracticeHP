package postwithauthapi3;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import allpojos.Credentials;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class AuthAPItest_TC1 {
	
	@Test
	public void getAuthTokenTest_withhardcodedBodytc1() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
          
		String tokenId = given()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}")
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.extract()
		.path("token");
		
		System.out.println("token id generated "+tokenId);
		Assert.assertNotNull(tokenId);
		
	}
	
	@Test
	public void getAuthTokenTest_withJSONFiletc2() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
          
		String tokenId = given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("./src\\test\\resources\\jsonssep26\\AuthData.json"))
		.when()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.extract()
		.path("token");
		
		System.out.println("token id generated "+tokenId);
		Assert.assertNotNull(tokenId);
		
	}
    
	@Test
	public void getAuthTokenTest_withPOJOtc3() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Credentials cred = new Credentials("username", "password");
          
		ValidatableResponse tokenId = given().log().all()
		.contentType(ContentType.JSON)
		.body(cred)
		.when()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.extract()
		.path("token");
		
		System.out.println("token id generated "+tokenId);
		Assert.assertNotNull(tokenId);
		
	}

}
