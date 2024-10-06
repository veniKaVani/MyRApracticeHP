package postwithauthapi3;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateGoRestApiUserTest {
	
	@Test
	public void createUserUsing_JSONFileTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Object userId = given()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		.contentType(ContentType.JSON)
		.body(new File("./src\\test\\resources\\jsonssep26\\GoRestUser.json"))
		.when()
		.post("/public/v2/users")
		.then()
		.assertThat()
		.statusCode(201)
		.and()
		.extract()
		.path("id");
		
		System.out.println("user id created: "+userId);
		Assert.assertNotNull(userId);
		
		
	}

}
