package restassured_notes;

public class Session2_RA {
	
	/*
	 * 02_GetCall_QueryParam_PathParam_extractMethod_JsonPath_GetInt_GetString
_GetLIst
**********************************************************GoRest apis

GoRest Token:
Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd

To segregate the data between two query params--AND(& symbol)-- is used in
the url section of PM
How to do the same in the rest assured code on eclipse???
-------------------------below is the complete chain that we have created
with the given, we have added the header also and query params as well
---------------------------------

@Test
public void getUserWith_QueryParams(){
	RestAssured.baseURI = "https://gorest.co.in";

	given()
	   .queryParam("name","triveni")
	     .queryParam("status", "active")
	       .when().log().all()
	         .get("/public/v2/users")
	            .then().log().all()
	              .assertThat()
	                .statusCode(200)
	                  .and()
	                    .contentType(ContentType.JSON);


}
---another test which retrieves data from a hash map:----------->
@Test
	public void getUserWith_QueryParams_WithHashMap() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Map<String, String>queryMap = new HashMap<String, String>();
		queryMap.put("name", "trivedi");
		queryMap.put("status", "active");
		queryMap.put("gender", "male");
		
		given().log().all()
		  .header("Authorization", "Bearer e6eec72969defa5772b22533590ebc176ed8184fb982e6582b5eff0f4cbf8cdd")
		   
		    .queryParams(queryMap)
		      .when().log().all()
		        .get("/public/v2/users")
		          .then().log().all()
		            .assertThat()
		              .and()
		                .contentType(ContentType.JSON);
		  
	}
	--------this again is bit tedious and there needs some optimization of code---so in jdk 9 onwards--they introduced a new method dot of()
	---which says u can give multiple key, value pairs--and u just need to
	supply key, values as params

	 Map.of(
	   "name", "trivedi",
	   "status", "active"
	 )

	 -----the above 
	 Map.of(
	   "name", "trivedi",
	   "status", "active"
	 )
	 ----is called immutable hashmap==> once this hashmap is created---u
	 cannot update anything

	 NOW CREATING ONE MORE TEST--USING PATH PARAMETER
   --------------------------first trying the path param url on PM
   GET /public/v2/users/6940718/posts	Retrieves user posts

    Using the below id in the above url--which retrieves the particular
    user
	 id: 7384609

	 GET /public/v2/users/7384609/posts 

	 so the path param is
	 /7384609/posts
HW] Supply trivedi and status of trivedi thru Data Provider???***********

--------------------------
create a new class to test the query param(?) and path param(/)
--to supply the path param in pm we use colon(:)
--to segregate two param in the url can use & symbol
using https://gorest.co.in for this test class as a baseURI


@DataProvider
---------------
How to use a dataprovider in testng??

@Test(dataprovider = "getData")
Test Case - LoginTest(username, password)

 @DataProvider
 getData(){
Data 1 - un, pwd
Data 2
Data 3
Data 4
Data 5
         }


 excel - testdata.xlsx

 3 things:
  1. excel file with test data
  2. at dataprovider
  3. test cases with the help of the data provider
  ------------------using Etsy website for this test
  username: bkarnam74@gmail.com
  password: Mango@23
  ------------  

	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}
