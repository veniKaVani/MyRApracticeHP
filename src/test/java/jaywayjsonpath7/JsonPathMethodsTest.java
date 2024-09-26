package jaywayjsonpath7;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathMethodsTest {
	
	@Test
	public void getProductsTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response getRes = given()
		.when()
		.get("/products")
		.then()
		.extract()
		.response();
		
		String jsonRes = getRes.asString();
		System.out.println(jsonRes);
		
		//min() - provides the min value of an array of numbers:
//		Double minPrice = JsonPath.read(jsonRes, "min($[*].price)");
//		System.out.println(minPrice);
		
		//max() - provides the max value of an array of numbers:
		
		
		
		
	}

}
