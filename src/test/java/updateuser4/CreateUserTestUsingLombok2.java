package updateuser4;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import updateuser4.UserLombok.UserLombokBuilder;

public class CreateUserTestUsingLombok2 {
	
	@Test
	public void createUserTest_WithUserLombok() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		UserLombok ul = new UserLombok("Imly",getRandomEmail(), "female", "active");
		
		//1.post: create a user
				Response postRes = given()
				.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(ul)
				.when()
				.post("/public/v2/users");
				
				postRes.prettyPrint();
				
				JsonPath js = postRes.jsonPath();
				int userId = js.getInt("id");
				System.out.println("user id created:"+userId);
	}
	
	@Test
	public void createUserTest_WithLombokBuilder() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		UserLombok ul = new UserLombok.UserLombokBuilder()
				.name("Pooja")
				.email(getRandomEmail())
				.gender("female")
				.status("inactive")
				.build();
		
		//1.post: create a user
				Response postRes = given()
				.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(ul)
				.when()
				.post("/public/v2/users");
				
				postRes.prettyPrint();
				
				JsonPath js = postRes.jsonPath();
				int userId = js.getInt("id");
				System.out.println("user id created:"+userId);
	}
	
	public String getRandomEmail() {
		return "PoojaAutoGp"+System.currentTimeMillis()+"app@fake.com";
	}

}
