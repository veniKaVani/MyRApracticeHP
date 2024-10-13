package restassuredduplicatecode;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class ContactsApiTests1_WithBDD {
	
	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
	}
	
	@Test
	public void getAllContacts_TestTC1() {
		
	//	RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmE4YWM4NjFiMjA2NTAwMTM0MmRlZTkiLCJpYXQiOjE3MjgzMzk4NzB9.ljtaXkuMpEfzSBuMtEoavcXH8etqex31RZZVOwEzqFQ")
		.when().log().all()
		.get("/contacts")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.statusLine("HTTP/1.1 200 OK")
		.and()
		.contentType(ContentType.JSON)
		.body("$.size()", equalTo(36));
		
	}
	
	@Test
	public void getAllContactsNegWithInvalidToken_TestTC2() {
		
		
		
		given().log().all()
		.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmE4YWM4NjFiMjA2NTAwMTM0MmRlZTkiLCJpYXQiOjE3MjgzMzk4NzB9.ljtaXkuMpEfzSBuMtEoBinkaAutomation")
		.when().log().all()
		.get("/contacts")
		.then().log().all()
		.assertThat()
		.statusCode(401)
		.and()
		.statusLine("HTTP/1.1 401 Unauthorized");
		
		
	}

	

}
