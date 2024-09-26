package getapitests1_withbdd;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

public class ProductAPIsRA1 {
	
	//BDD style test: given -->when -->then
	@Test
	public void getProductsTest_1() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
		 .when()
		 .get("/products")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200)
		 .and()
		 .statusLine("HTTP/1.1 200 OK");
	
	}
	
	@Test
	public void getProductsTest_2() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
		 .when()
		 .get("/products")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200)
		 .and()
		 .body("$.size()", equalTo(20));
	
	}

}
