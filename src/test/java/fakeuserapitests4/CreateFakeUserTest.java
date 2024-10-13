package fakeuserapitests4;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import fakeuserapitests4.FakeUserLombok.Address;
import fakeuserapitests4.FakeUserLombok.Address.Geolocation;
import io.restassured.RestAssured;

public class CreateFakeUserTest {
	
	@Test
	public void createFakeUser_withLombok1() {
	 RestAssured.baseURI = "https://fakestoreapi.com";
	 
//	 Geolocation geoLoc = new Geolocation("-37.3159", "81.1496");
//	 Address.Geolocation geoLoc = new Geolocation("-37.3159", "81.1496");
	 
	// FakeUserLombok.Name name = new FakeUserLombok.Name();
	 
	// FakeUserLombok.Address address = new FakeUserLombok.Address("Hyderabad", "Road10", 57, "500034", geoLoc);
	 
	 FakeUserLombok.Address.Geolocation geoLoc 
	                = new FakeUserLombok.Address.Geolocation("-98.3159", "98.1496");
	 
	 FakeUserLombok.Address address = new FakeUserLombok.Address("Hyderabad", "Road10", 57, "500034", geoLoc);
	 
	 FakeUserLombok.Name name = new FakeUserLombok.Name("BalaKrishna","Pillai");
	 
	 FakeUserLombok FUL = new FakeUserLombok("BalaK15@fake.com", "BalaKrishna", "BalaK@2015", "1986-6085-982", name, address);
	 
	 given().log().all()
	   .body(FUL)
	    .when().log().all()
	      .post("/users")
	      .then().log().all()
	      .assertThat()
	      .statusCode(200);
		
	}
	
	@Test
	public void createFakeUserTest_withLombokBuilder2() {
		 RestAssured.baseURI = "https://fakestoreapi.com";
		 
		 FakeUserLombok.Address.Geolocation geoLoc 
         = new FakeUserLombok.Address.Geolocation.GeolocationBuilder() 
         .lat("-90.977")
         .longitude("654.55")
         .build();
		 
		 FakeUserLombok.Address address = new FakeUserLombok.Address.AddressBuilder()
				                          .city("Poona")
				                          .street("Anganwadi")
				                          .number(20)
				                          .zipcode("600024")
				                          .geoLoc(geoLoc)
				                          .build();
		 
		 FakeUserLombok.Name name = new FakeUserLombok.Name.NameBuilder()
				                    .firstname("Piyush")
				                    .lastname("Sharma")
				                    .build();
				                    
		 FakeUserLombok FUL = new FakeUserLombok.FakeUserLombokBuilder()
				               .email("PiyushSh123@fake.com")
				               .username("Piyush@12")
				               .password("Papaya24")
				               .phone("732-876-2361")
				               .name(name)
				               .address(address)
				               .build();
	
			given().log().all()
			.body(FUL).when().log().all()
			.post("/users")
			.then().log().all()
			.assertThat()
			.statusCode(200);
		 
		 
	}

}
