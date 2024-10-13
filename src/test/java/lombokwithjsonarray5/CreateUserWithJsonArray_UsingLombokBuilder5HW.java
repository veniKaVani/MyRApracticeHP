package lombokwithjsonarray5;

import static io.restassured.RestAssured.given;

import java.util.Arrays;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombokwithjsonarray5.User.Support.SupportBuilder;

public class CreateUserWithJsonArray_UsingLombokBuilder5HW {
	
	@Test
	public void createUserWithJsonArrayPojo() {
		
		RestAssured.baseURI = "http://httpbin.org";
		
		User.UserData ud1 =	User.UserData.builder().id(1).email(getRandomEmail()).first_name("Nita").last_name("Saakshi").avatar("https://reqres. in/img/faces/7-image. jpg").build();
		User.UserData ud2 =	User.UserData.builder().id(2).email(getRandomEmail()).first_name("Gita").last_name("Kumar").avatar("https://reqres. in/img/faces/8-image. jpg").build();
		User.UserData ud3 =	User.UserData.builder().id(3).email(getRandomEmail()).first_name("Sita").last_name("Ram").avatar("https://reqres. in/img/faces/9-image. jpg").build();
		User.UserData ud4 =	User.UserData.builder().id(4).email(getRandomEmail()).first_name("Rita").last_name("Kanum").avatar("https://reqres. in/img/faces/5-image. jpg").build();
		User.UserData ud5 =	User.UserData.builder().id(5).email(getRandomEmail()).first_name("Mita").last_name("Kashyap").avatar("https://reqres. in/img/faces/6-image. jpg").build();
		User.UserData ud6 =	User.UserData.builder().id(6).email(getRandomEmail()).first_name("Pita").last_name("Patel").avatar("https://reqres. in/img/faces/4-image. jpg").build();
		
		
		
		
	//	User.UserData ud1 = new User.UserData(1,getRandomEmail(),"Nita","Saakhsi","https://reqres. in/img/faces/7-image. jpg");
//			User.UserData ud2 = new User.UserData(2,getRandomEmail(),"Gita","Kumar","https://reqres. in/img/faces/8-image. jpg");
//				User.UserData ud3 = new User.UserData(3,getRandomEmail(),"Sita","Ram","https://reqres. in/img/faces/9-image. jpg");
//					User.UserData ud4 = new User.UserData(4,getRandomEmail(),"Rita","Kanum","https://reqres. in/img/faces/5-image. jpg");
//						User.UserData ud5 = new User.UserData(5,getRandomEmail(),"Mita","Kashyap","https://reqres. in/img/faces/6-image. jpg");
//							User.UserData ud6 = new User.UserData(6,getRandomEmail(),"Pita","Patel","https://reqres. in/img/faces/4-image. jpg");
							
//			User.Support s = new User.Support("https://reqres.in/#support-heading","To keep ReqRes free,contributions towards server costs are not appreciated!");
			
	User.Support s = new User.Support.SupportBuilder().url("https://reqres.in/#support-heading").text("To keep ReqRes free,contributions towards server costs are not appreciated!").build();
	
			
//			User user  = new User(
//					1,
//					2,
//					12,
//					2,
//					Arrays.asList(ud1,ud2,ud3,ud4,ud5,ud6),
//					s
//					);
	
	User user = new User.UserBuilder().page(1).per_page(2).total(12).total_pages(2).data(Arrays.asList(ud1,ud2,ud3,ud4,ud5,ud6)).support(s).build();
			
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
