package oauthapitest10;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpotifyApiTests {
	
	private String accessToken;
	
	@BeforeMethod
	public void getAccessToken() {
		RestAssured.baseURI = "https://accounts.spotify.com";
		
		Response postRes = RestAssured.given()
		          .contentType(ContentType.URLENC)
		          .formParam("grant_type", "client_credentials")
		          .formParam("client_id", "be1ff9a855da4dff87a565fd62a77140")
		          .formParam("client_secret", "57fef4a27d8a4463943327553f7393b6")
		          .when()
		          .post("/api/token");
		
		Assert.assertEquals(postRes.getStatusCode(), 200);
		postRes.prettyPrint();
		
		accessToken=postRes.jsonPath().getString("access_token");
		
	}
	
	@Test
	public void getAlbumTest() {
		
		Response getRes = RestAssured.given()
		          .header("Authorization", "Bearer "+accessToken)
		          .when()
		          .get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");
		
		Assert.assertEquals(getRes.getStatusCode(), 200);
		getRes.prettyPrint();
		          
		          
		
	}

}
