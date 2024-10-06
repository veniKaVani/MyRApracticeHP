package oauthapitest10;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth1Test {
	
	//Oauth1.0:you need to add extra dependency in your pom.xml
	//name: scribejava-core
	//version: 2.5.3  only this old version works
	
	@Test
	public void flickrApiOauth1Test() {
		
		RestAssured.baseURI = "https://www.flickr.com";
		
		Response getRes = RestAssured.given()
		         .auth()
		         .oauth("e22b7aab0af32d194e3e0bd5c1188173", 
		        		 "51769bf47828e894", 
		        		 "72157720930297761-acbc7a8a88239383",
		        		 "9213992cda744824")
		         .queryParam("nojsoncallback", 1)
		         .queryParam("format", "json")
		         .queryParam("method", "flickr.test.login")
		         .when()
		         .get("/services/rest")
		         .then()
		         .assertThat()
		         .statusCode(200)
		         .extract()
		         .response();
		
		System.out.println(getRes.prettyPrint());
		         
		
	}

}
