package restassuredduplicatecode;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojosduplicate.Credentials;

public class PostWithAuthApi3_fetchToken {
	
	@Test
	public void getAuthTokenTest_WithHardcodedJson1() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Object tokenId = given().log().all()
		  .contentType(ContentType.JSON)
		  .body("{\r\n"
		  		+ "    \"username\" : \"admin\",\r\n"
		  		+ "    \"password\" : \"password123\"\r\n"
		  		+ "}")
		  .when().log().all()
		  .post("/auth")
		  .then().log().all()
		  .assertThat()
		  .statusCode(200)
		  .and()
		  .extract()
		  .path("token");
		
		System.out.println("token generated: "+tokenId);
		Assert.assertNotNull(tokenId);
		
		
	}
	
	@Test
	public void getAuthTokenTest_WithJsonFile2() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Object tokenId = given().log().all()
		  .contentType(ContentType.JSON)
		  .body(new File("./\\src\\test\\resources\\newjsonsduplicatecode\\AuthData.json"))
		  .when().log().all()
		  .post("/auth")
		  .then().log().all()
		  .assertThat()
		  .statusCode(200)
		  .and()
		  .extract()
		  .path("token");
		
		System.out.println("token generated: "+tokenId);
		Assert.assertNotNull(tokenId);
		
		
	}
	
	@Test
	public void getAuthTokenTest_WithPOJOClass3() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//creating the object of Credentials class:
		Credentials cred2 = new Credentials("admin", "password123");
		
		Object tokenId = given().log().all()
		  .contentType(ContentType.JSON)
		  .body(cred2)
		  .when().log().all()
		  .post("/auth")
		  .then().log().all()
		  .assertThat()
		  .statusCode(200)
		  .and()
		  .extract()
		  .path("token");
		
		System.out.println("token generated: "+tokenId);
		Assert.assertNotNull(tokenId);
		
		
	}


}
