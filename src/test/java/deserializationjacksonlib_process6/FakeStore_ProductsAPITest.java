package deserializationjacksonlib_process6;

import static io.restassured.RestAssured.given;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FakeStore_ProductsAPITest {
	
	@Test
	public void getAllProductsTest_With_POJOdeserialization() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response getRes = given()
		.when()
		.get("/products");
		
		getRes.prettyPrint();
		
		//De-Serialization: response json -- Pojo(products)
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			FakeProduct[] userRes = mapper.readValue(getRes.getBody().asString(), FakeProduct[].class);
		
		    System.out.println(Arrays.toString(userRes));
		    
		    for(FakeProduct p:userRes) {
		    	System.out.println(p.getId());
		    	System.out.println(p.getTitle());
		    	System.out.println(p.getPrice());
		    	System.out.println(p.getDescription());
		    	System.out.println(p.getCategory());
		    	System.out.println(p.getImage());
		    	System.out.println(p.getRating().getRate());
		    	System.out.println(p.getRating().getCount());
		    }
		
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
