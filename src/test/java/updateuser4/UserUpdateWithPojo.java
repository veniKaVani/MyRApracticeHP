package updateuser4;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserUpdateWithPojo {
	//1.create a user: POST
	//2.GET
	//3.update a user: PUT
	
	@Test
	public void createGoRestUserWithPojo_Test1() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User("Alice", getRandomEmail(), "female", "inactive");
		
		//1.post: create a user
		Response postRes = given()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(user)
		.when()
		.post("/public/v2/users");
		
		postRes.prettyPrint();
		
		JsonPath js = postRes.jsonPath();
		int userId = js.getInt("id");
		System.out.println("user id created:"+userId);
		
		//3.update the user: UPDATE
		
		user.setName("AliceRoy");
		user.setStatus("active");
		
		given()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(user)
		.when()
		.put("/public/v2/users/"+userId)
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("id", equalTo(userId))
		.body("name", equalTo("AliceRoy"))
		.and()
		.body("status", equalTo("active"));
		
		
		
		
	}
	
  public String getRandomEmail() {
	  return "BinkaGpAuto"+System.currentTimeMillis()+"app@fake.com";
  }
}
