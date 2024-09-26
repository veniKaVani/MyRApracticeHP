package xmlapis8;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XmlPathTest {
	
	@Test
	public void circuitAPITest_Xml() {
		RestAssured.baseURI = "https://ergast.com";
		
		Response getRes = given()
		.when()
		.get("/api/f1/2017/circuits.xml")
		.then()
		.extract()
		.response();
		
		String resBody = getRes.body().asString();
		System.out.println(resBody);
		
		XmlPath xmlPath = new XmlPath(resBody);
		
		List<String> circuitNames = xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");		
		System.out.println("total circuits: "+circuitNames.size());
		System.out.println("------------------------------------");
		
		for(String e:circuitNames) {
			System.out.println(e);
		}
		System.out.println("------------------------------------");
		
		//fetch the locality where circuit Id = 'americas'==>Austin
		String locality = xmlPath.getString("**.findAll{it.@circuitId=='americas'}.Location.Locality");
		System.out.println(locality);
	}

}
