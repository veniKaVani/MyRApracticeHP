package jaywayjsonpath7;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPath__FakeStoreTest {

	@Test
	public void getProductAPITest_JsonPath() {

		RestAssured.baseURI = "https://fakestoreapi.com";

		Response getRes = given().when().get("/products");

		getRes.prettyPrint();
		String jsonRes = getRes.asString();
		System.out.println(jsonRes);

		DocumentContext docContext = JsonPath.parse(jsonRes);

		List<Number> priceList = docContext.read("$[?(@.price >50)].price");
		System.out.println(priceList);

		List<Integer> allIds = docContext.read("$[?(@.price >50)].id");
		System.out.println(allIds);

		List<String> allTitles = docContext.read("$[?(@.price >50)].title");
		System.out.println(allTitles);

		List<Double> allRates = docContext.read("$[?(@.price >50)]rating.rate");
		System.out.println(allRates);

		List<Integer> allCounts = docContext.read("$[?(@.price >50)]rating.count");
		System.out.println(allCounts);

		for (int i = 0; i < allIds.size(); i++) {
			System.out.println(allIds.get(i) + " " + priceList.get(i));
		}

		for (int i = 0; i < allIds.size(); i++) {
			System.out.println(allRates.get(i) + " " + allCounts.get(i));
		}

	}

	@Test
	public void getProductAPITest_ConditionalExamples_withTwoAttributes() {

		RestAssured.baseURI = "https://fakestoreapi.com";

		Response getRes = given().when().get("/products");

		getRes.prettyPrint();
		String jsonRes = getRes.asString();
		System.out.println(jsonRes);

		DocumentContext docContext = JsonPath.parse(jsonRes);

		// single attribute:
		// $[?(@.rating.rate<3)]

		// with two attributes:
		List<Map<String, Object>> infoMap = docContext.read("$[?(@.category=='jewelery')].['title','price']");
		System.out.println(infoMap);
		System.out.println(infoMap.size());

		for (Map<String, Object> e : infoMap) {
			System.out.println(e.get("title"));
			System.out.println(e.get("price"));
			System.out.println(e.get("id"));
		}

	}

	@Test
	public void getProductAPITest_ConditionalExamples_withThreeAttributes() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response getRes = given()
		.when()
		.get("/products");
		
		getRes.prettyPrint();
		String jsonRes = getRes.asString();
		System.out.println(jsonRes);
		
		DocumentContext docContext = JsonPath.parse(jsonRes); 
		
		//with three attributes:
		List<Map<String, Object>> infoMap = docContext.read("$[?(@.category=='jewelery')].['title','price','id']");
		System.out.println(infoMap);
		System.out.println(infoMap.size());
				
		for(Map<String, Object> e:infoMap) {
		String title = (String)e.get("title");
		Number price = (Number)e.get("price");
		Integer id = (Integer)e.get("id");
		
		System.out.println("title:"+title);
		System.out.println("price:"+price);
		System.out.println("id:"+id);
		System.out.println("-------------");
				}
	}
	//two conditional statements
	//   $[?((@.id==3)&&(@.username=='kevinryan'))].address.city
}