package homework_api;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;

public class GoRest_UsersInfoValidations8HW {
				
			@Test
			public void getIdDataTypesTest() {
				
				RestAssured.baseURI = "https://gorest.co.in";
				
				Response getRes = given()
				.when()
				.get("/public/v2/users.xml")
				.then()
				.extract()
				.response();
				
				String response = getRes.body().asString();
				System.out.println(response);
				
				//creating the obj of xmlPath:
				XmlPath XP = new XmlPath(response);
				List<Object> dataTypeLi = XP.getList("objects.object.id.@type");
				for(Object dT:dataTypeLi) {
					System.out.println(dT);
					Assert.assertNotNull(dT);
				}
				
				
			}
			@Test
			public void getUsersInfoGoRestTest() {
				
				RestAssured.baseURI = "https://gorest.co.in";
				
				Response getRes = given()
				.when()
				.get("/public/v2/users.xml")
				.then()
				.extract()
				.response();
				
				String response = getRes.body().asString();
				System.out.println(response);
				
				//creating the obj of xmlPath:
				XmlPath XP = new XmlPath(response);
				List<String> allIds = XP.getList("objects.object.id");
				System.out.println(allIds.size());
				for(String str:allIds) {
					System.out.println(str);
				}
				System.out.println("-----------------");
				
				//fetching the names:
				List<String> allNames = XP.getList("objects.object.name");
				for(String N:allNames) {
					System.out.println(N);
				}
				System.out.println("-----------------");
				
				
			}
		

	}


