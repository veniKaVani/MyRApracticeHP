package getapitests1_withoutbdd;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ProductAPIsRA1 {
	
	//without BDD
	@Test
	public void getProductsTest_1() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmE4YWM4NjFiMjA2NTAwMTM0MmRlZTkiLCJpYXQiOjE3MjY4NTQ5NTJ9.4_7uh4ezHfRFQgs9CvM2fe60Wzba1dg3YoQrOaLWy18");
		
		Response getRes = reqSpec.get("/contacts");
		int statusCode = getRes.statusCode();
		System.out.println("status code: "+statusCode);
		
		String statusLine = getRes.statusLine();
		System.out.println("status line: "+statusLine);
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		ResponseBody resBody = getRes.body().prettyPeek();
		System.out.println(resBody);
	}

}
