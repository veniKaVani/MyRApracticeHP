package homework_api;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpotifyAPIs10_AllGetTestsHW {
	
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
	public void getArtistOAuth2Test_TC1HW() {
		
		Response getRes = RestAssured.given()
		           .header("Authorization", "Bearer "+accessToken)
		           .when()
		           .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");
		
		Assert.assertEquals(getRes.getStatusCode(), 200);
		getRes.prettyPrint();
		
		
	}
	
	@Test
	public void getAnAudioBookOAuth2Test_TC2HW() {
		
		Response getRes = RestAssured.given()
		           .header("Authorization", "Bearer "+accessToken)
		           .when()
		           .get("https://api.spotify.com/v1/audiobooks/7iHfbu1YPACw6oZPAFJtqe");
		
		Assert.assertEquals(getRes.getStatusCode(), 200);
		getRes.prettyPrint();
		
	}
	
	@Test
	public void getAChapterOAuth2Test_TC3HW() {
		
		Response getRes = RestAssured.given()
		           .header("Authorization", "Bearer "+accessToken)
		           .when()
		           .get("https://api.spotify.com/v1/chapters/0D5wENdkdwbqlrHoaJ9g29");
		
		Assert.assertEquals(getRes.getStatusCode(), 200);
		getRes.prettyPrint();
		
	}
	
	@Test
	public void getEpisodeOAuth2Test_TC4HW() {
		Response getRes = RestAssured.given()
		           .header("Authorization", "Bearer "+accessToken)
		           .when()
		           .get("https://api.spotify.com/v1/episodes/512ojhOuo1ktJprKbVcKyQ");
		
		Assert.assertEquals(getRes.getStatusCode(), 404);
		getRes.prettyPrint();
	}
	
	@Test
	public void getAvailableGenreSeedsOAuth2Test_TC5HW() {
		Response getRes = RestAssured.given()
		           .header("Authorization", "Bearer "+accessToken)
		           .when()
		           .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
		
		Assert.assertEquals(getRes.getStatusCode(), 200);
		getRes.prettyPrint();
	}
	
	@Test
	public void getAvailableMarketsOAuth2Test_TC6HW() {
		Response getRes = RestAssured.given()
		           .header("Authorization", "Bearer "+accessToken)
		           .when()
		           .get("https://api.spotify.com/v1/markets");
		
		Assert.assertEquals(getRes.getStatusCode(), 200);
		getRes.prettyPrint();
		
	}

}
