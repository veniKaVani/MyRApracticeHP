package getapitests1_withbdd;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

public class GetContactsAPIsTest {
	
	@Test
	public void getContactsList_Test1() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		 .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmE4YWM4NjFiMjA2NTAwMTM0MmRlZTkiLCJpYXQiOjE3MjY4NTQ5NTJ9.4_7uh4ezHfRFQgs9CvM2fe60Wzba1dg3YoQrOaLWy18")
		 .when().log().all()
		 .get("/contacts")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200)
		 .and()
		 .statusLine("HTTP/1.1 200 OK")
		 .and()
		 .contentType(ContentType.JSON)
		 .and()
		 .body("$.size()", equalTo(36));
		 
		  
		
		
		
	}
	
	@Test
	public void getContactsList_Test2_NegativeInvalidToken() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		 .header("Authorization", "Bearer -AbcxyZ6543")
		 .when().log().all()
		 .get("/contacts")
		 .then().log().all()
		 .assertThat()
		 .statusCode(401);
		 
		 
		  
		
		
		
	}

}
