package authapis9;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OAuth2APITest {
	
	private Object accessToken;
	
	@BeforeMethod
	public void getAuthAccessToken() {
		RestAssured.baseURI = "https://test.api.amadeus.com";
		
		 Response postRes = RestAssured.given().log().all()
		   .contentType(ContentType.URLENC)
		   .formParam("grant_type", "client_credentials")
		   .formParam("client_id", "zViieMuKm8MUQbGmmbM53RtTw48AfEPc")
		   .formParam("client_secret", "IhSF55C9IUJ4LEhS")
		   .when().log().all()
		   .post("/v1/security/oauth2/token");
		 
		 Assert.assertEquals(postRes.getStatusCode(), 200);
		 postRes.prettyPrint();
		 
		accessToken = postRes.jsonPath().getString("accessToken");
		 System.out.println("access token generated:"+accessToken);
		  
	}
	
	@Test
	public void getFlightDetailsTest_1() {
		RestAssured.baseURI = "https://test.api.amadeus.com";
		
		Response getRes = RestAssured.given().log().all()
		.header("Authorization","Bearer "+accessToken)
		.when().log().all()
		.get("/v1/shopping/flight-destinations?origin=PAR&maxPrice=200");
		
		Assert.assertEquals(getRes.statusCode(), 200);
		getRes.prettyPrint();
		
		
		
	} 
	

}
