package deserializationjacksonlib_process6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPITestWithJsonArrayResponse {
	
	@Test
	public void GetAllUsers_JsonArrayUsingLombokTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//1.get all users using: GET
		Response getRes = given().log().all()
		.header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		.when().log().all()
		.get("/public/v2/users");
		
		getRes.prettyPrint();
		//serialization is not applicable-since no payload body
		
		//De-serialization: JSON to POJO
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			UserLombok[] userRes = mapper.readValue(getRes.getBody().asString(), UserLombok[].class);
			for(UserLombok e:userRes) {
				System.out.println("ID:"+e.getId());
				System.out.println("name:"+e.getName());
				System.out.println("email:"+e.getEmail());
				System.out.println("gender:"+e.getGender());
				System.out.println("status:"+e.getStatus());
				
				System.out.println("----------------------");
			}
		
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}

}
