package lombokwithjsonarray5;

import java.util.Arrays;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateUserWithJsonArrayLombokPOJO {
	
	@Test
	public void createUserWithJsonArrayPojo() {
		//httpbin.org/post
		RestAssured.baseURI = "http://httpbin.org";
		
		User.UserData ud1 = new User.UserData(1,"NitaS24@fake.com","Nita","Saakhsi","https://reqres. in/img/faces/7-image. jpg");
			User.UserData ud2 = new User.UserData(2,"GitaK24@fake.com","Gita","Kumar","https://reqres. in/img/faces/8-image. jpg");
				User.UserData ud3 = new User.UserData(3,"SitaS24@fake.com","Sita","Ram","https://reqres. in/img/faces/9-image. jpg");
					User.UserData ud4 = new User.UserData(4,"RitaKa24@fake.com","Rita","Kanum","https://reqres. in/img/faces/5-image. jpg");
						User.UserData ud5 = new User.UserData(5,"MitaKas24@fake.com","Mita","Kashyap","https://reqres. in/img/faces/6-image. jpg");
							User.UserData ud6 = new User.UserData(6,"PitaP24@fake.com","Pita","Patel","https://reqres. in/img/faces/4-image. jpg");
							
			User.Support s = new User.Support("https://reqres.in/#support-heading","To keep ReqRes free,contributions towards server costs are not appreciated!");
			
			User user  = new User(
					1,
					2,
					12,
					2,
					Arrays.asList(ud1,ud2,ud3,ud4,ud5,ud6),
					s
					);
			
			given().log().all()
			.contentType(ContentType.JSON)
			.body(user)
			.when().log().all()
			.post("/post")
			.then().log().all()
			.assertThat()
			.statusCode(200);
			
	}
  public String getRandomEmail() {
	  return "veniKa"+System.currentTimeMillis()+"app24@fake.com";
  }
}
