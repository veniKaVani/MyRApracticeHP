package restfulbooker_apitests3;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class BookingAPIFeatureTests {
	
	String tokenId;
	
	@BeforeMethod
	public void getAuthApi_tokenTest() {
			
			RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	          
			 tokenId = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("./src\\test\\resources\\jsonssep26\\AuthData.json"))
			.when()
			.post("/auth")
			.then().log().all()
			.assertThat()
			.statusCode(200)
			.and()
			.extract()
			.path("token");
			
			System.out.println("token id generated "+tokenId);
			Assert.assertNotNull(tokenId);
			
		}
	
	@Test
	public void getBookingIdsTest_tc1() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Response getRes = given()
		.when()
		.get("/booking")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.extract()
		.response();
		
		JsonPath js = getRes.jsonPath();
		List<Object> allBookingIds = js.getList("bookingid");
		System.out.println("total booking ids: "+allBookingIds.size());
		
		for(Object id:allBookingIds) {
			System.out.println(id);
			Assert.assertNotNull(id);
		}
		
	
	}
	
	@Test
	public void getBookingTest_tc2() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//create a new booking:
		Object newBookingId = createBooking();
		
		//get the same booking:
		given().log().all()
		.pathParam("bookingId", newBookingId)
		.when().log().all()
		.get("/booking/{bookingId}")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("firstname", equalTo("Tim"))
		.and()
		.body("bookingdates.checkin", equalTo("2018-01-01"));
		
	}
	
	@Test
	public void createBookingTest_tc3() {
		Assert.assertNotNull(createBooking());
	}
	
	@Test
	public void UpdateBookingTest_tc4() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//create new booking:
		Object newBookingId = createBooking();
		
		//get the same booking:
		given().log().all()
		.pathParam("bookingId", newBookingId)
		.when().log().all()
		.get("/booking/{bookingId}")
		.then().log().all()
		.assertThat()
		.statusCode(200);
		
		//update the same booking:
		given().log().all()
		.pathParam("bookingId", newBookingId)
		.contentType(ContentType.JSON)
		.header("Cookie", "token="+tokenId)
		.body("{\r\n"
				+ "    \"firstname\" : \"Kim\",\r\n"
				+ "    \"lastname\" : \"Babluk\",\r\n"
				+ "    \"totalprice\" : 106,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Dinner\"\r\n"
				+ "}")
		.when().log().all()
		.put("/booking/{bookingId}")
		.then().log().all()
		.assertThat()
		.statusCode(200)
        .and()
        .body("firstname", equalTo("Kim"))
        .and()
        .body("lastname", equalTo("Babluk"))
        .and()
        .body("additionalneeds", equalTo("Dinner"));
        
	}
	
	@Test
	public void PartialBookingTest_tc5() {
		
RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//create new booking:
		Object newBookingId = createBooking();
		
		//get the same booking:
		given().log().all()
		.pathParam("bookingId", newBookingId)
		.when().log().all()
		.get("/booking/{bookingId}")
		.then().log().all()
		.assertThat()
		.statusCode(200);
		
		//update partial booking:
		given().log().all()
		.pathParam("bookingId", newBookingId)
		.contentType(ContentType.JSON)
		.header("Cookie", "token="+tokenId)
		.body("{\r\n"			
				+ "    \"lastname\" : \"Babuk\",\r\n"
				+ "    \"totalprice\" : 107,\r\n"
				+ "}")
		.when()
		.patch("/booking/{bookingId}")
		.then()
		.assertThat()
		.statusCode(200);
		
		
	}
	
	public Object createBooking() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Object bookingId = given().log().all()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"firstname\" : \"Tim\",\r\n"
				+ "    \"lastname\" : \"Babak\",\r\n"
				+ "    \"totalprice\" : 106,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Lunch\"\r\n"
				+ "}")
		.when().log().all()
		.post("/booking")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.extract()
		.path("bookingid");
		
		System.out.println("booking id: "+bookingId);
		return bookingId;
	}
	
	}


