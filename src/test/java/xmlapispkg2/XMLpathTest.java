package xmlapispkg2;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLpathTest {

	@Test
	public void circuitAPItest_withXml() {
		RestAssured.baseURI = "https://ergast.com";

		Response getRes = given().when().get("/api/f1/2017/circuits.xml").then().extract().response();

		String response = getRes.body().asString();
		System.out.println(response);

		// create the obj of xmlPath:
		XmlPath XP = new XmlPath(response);
		List<String> circuitNames = XP.getList("MRData.CircuitTable.Circuit.CircuitName");
		System.out.println("total circuits: " + circuitNames.size());

		for (String e : circuitNames) {
			System.out.println(e);
		}

		System.out.println("--------------------------");
		// fetch the locality where the circuit id = 'americas'==>austin
		// **findAll{it.@circuitId='americas'}.Location.Locality
		String locality = XP.getString("**.findAll{it.@circuitId=='americas'}.Location.Locality");
		System.out.println(locality);

		// fetchin the country:
		String country = XP.getString("**.findAll{it.@circuitId=='americas'}.Location.Country");
		System.out.println(country);

		// fetch the long of Bahrain and Sakhir:
		List<String> multipleCountries = XP
				.getList("**.findAll{it.@circuitId=='americas'||it.@circuitId=='bahrain'}.Location.Locality");
		System.out.println(multipleCountries);

	}

	@Test
	public void circuitAPItest2_withXml() {
		RestAssured.baseURI = "https://ergast.com";

		Response getRes = given().when().get("/api/f1/2017/circuits.xml").then().extract().response();

		String response = getRes.body().asString();
		System.out.println(response);

		// create the obj of xmlPath:
		XmlPath XP = new XmlPath(response);
		List<String> allCircuitIds = XP.getList("MRData.CircuitTable.Circuit.@circuitId");
		System.out.println(allCircuitIds);

		for (String e : allCircuitIds) {
			System.out.println(e);
		}
		System.out.println("-------------------------------------");
	}

	// for Bahrain get the lat n long values:
	@Test
	public void circuitAPItest3_withXml() {
		RestAssured.baseURI = "https://ergast.com";

		Response getRes = given().when().get("/api/f1/2017/circuits.xml").then().extract().response();

		String response = getRes.body().asString();
		System.out.println(response);

		// create the obj of xmlPath:
		XmlPath XP = new XmlPath(response);
		List<String> allCircuitIds = XP.getList("MRData.CircuitTable.Circuit.@circuitId");
		System.out.println(allCircuitIds);

		for (String e : allCircuitIds) {
			System.out.println(e);
		}
		System.out.println("-------------------------------------");

		String lat = XP.getString("**.findAll{it.@CircuitId=='Bahrain'}.Location.@lat");
		System.out.println(lat);

		String longitude = XP.getString("**.findAll{it.@CircuitId=='Bahrain'}.Location.@long");
		System.out.println(longitude);

	}
}