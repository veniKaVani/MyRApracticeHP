package restassuredduplicatecode;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ContactsAPIsTests1_WithoutBdd {
	//without Bdd:
	@Test
	public void getContactsListTests_1() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmE4YWM4NjFiMjA2NTAwMTM0MmRlZTkiLCJpYXQiOjE3MjgzMzk4NzB9.ljtaXkuMpEfzSBuMtEoavcXH8etqex31RZZVOwEzqFQ");
		
		Response getRes = reqSpec.get("/contacts");
		
		int statusCode = getRes.statusCode();
		System.out.println("status code on response:"+statusCode);
		
		Assert.assertEquals(statusCode, 200);
		
		String statusLine = getRes.statusLine();
		System.out.println("status Line on response:"+statusLine);
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		getRes.prettyPrint();
		
		
		
		
	}

}
