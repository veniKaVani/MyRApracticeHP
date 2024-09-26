package deserializationjacksonlib_process6;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPITest_usingLombok {
	
	@Test
	public void createUser_WithLombokTC1() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		UserLombok UL = new UserLombok.UserLombokBuilder()
				        .name("KushiK")
				        .email("KushiK@app458.com")
				        .gender("female")
				        .status("active")
				        .build();
		Response postRes = given()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		.contentType(ContentType.JSON)
        .body(UL)
        .when().log().all()
        .post("/public/v2/users");
        
		//fetch the user id: 
		postRes.prettyPrint();
		Integer userId_PR = postRes.jsonPath().get("id");
		System.out.println("userId created :"+userId_PR);
		System.out.println("============================");
		
		//2.get a user using the created user id: GET
		Response getRes = given().log().all()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		.when().log().all()
		.get("/public/v2/users"+userId_PR);
		
		getRes.prettyPrint();
		
		//3.De-Serialization: since res is json --has to be converted to pojo
		//json --> pojo: jackson databind lib--create Object Mapper class
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserLombok userRes = mapper.readValue(getRes.getBody().asString(), UserLombok.class);
			
			
			
			System.out.println(userRes.getId()+" "+userRes.getName()+" "+userRes.getEmail()+" "+userRes.getGender()+" "+userRes.getStatus());
			Assert.assertNotNull(userRes.getId());
			Assert.assertEquals(userRes.getName(), UL.getName());
			Assert.assertEquals(userRes.getEmail(), UL.getEmail());
			Assert.assertEquals(userRes.getGender(), UL.getGender());
			Assert.assertEquals(userRes.getStatus(), UL.getStatus());
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
 public String generateRandomEmail() {
	 return "Binka"+System.currentTimeMillis()+"@app24.com";
	 
 }
}
