package restassured_notes;

public class Session3_RA {
	
	/*
	 * 03_PostCall_With_JsonString_File_AuthAPI_BookingAPI_GET_POST_PUT_DELETE 
  ***********************************************************************
  How to create the Auth with the POst call using json??
  When the following code is written and after .body do ctrl+space and see the foll options for body()
---is an overloaded method
package authAPIwithPOST;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class AuthAPIpostcallTest {
	
	@Test
	public void getAuthTokenTest() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		   
		given()
		  .contentType(ContentType.JSON)
		    .bo
--------------------------------------now that the header content type is added to the post call
---you need to add body ---that can be done in 3 ways—1. Way copy the body part from cURL and paste directly as hard coded in the code inside the body()
--body copied from cURL is
curl -X POST \
  https://restful-booker.herokuapp.com/auth \
  -H 'Content-Type: application/json' \
  -d '{
    "username" : "admin",
    "password" : "password123"
}'




 

2) way is to create a file under src/main/resources OR src/test/resources –which has the body content as a file and then 
Give the path of this file in the test script to be picked from here---AND THE FILE SHOULD BE OF TYPE 
.json
JSON: DisAdvantage--ONLY FOR STATIC DATA WORKS
if the data is a huge file then it is a problem and also if the data is
dynamic--will not work
//using https://gorest.co.in/public/v2/users -- Create a new user
//throws 422 if email(is dynamic) is not new each time==>limitation of json==>the need for POJO 

if it is non-java file that you are creating--always right click, select folder and then create the file inside the folder
if it is java file that you are creating --always right click, select pkg and then create the file inside the pkge

3) way is by using POJO class, under src/main/java since this is a normal java class
 POJO: plain old java object, is a special class which will help to create an object of that particular class
 pojo cannot extend anything, cannot implement any thing, cannot inherit anything, cannot have a parent either--so no extends, no inherits, no implements
 --in the POJO class we have to create private data variables--that could be accessed thru public methods(Getters&Setters)
 ==> ENCAPSULATION (oops concept) of java
 -----whenever an object gets created --the constructor is called 
 =====POJO class should have the following==to call the POJO class object---create an object--and call thru object reference
 -cannot extend/implement anything
 -private data fields(variables) --1
 -public getter/setter
 -Encapsulation
 -public constructors--2 ====right click-->source-->Generate Constructors using the fields, remove super()--since we are not extending anything
 -create getters and setters--3====right click-->source-->Generate getters and setters
 under src/main/java --right click new --> create a package since this is java related and name it POJO
 And create a credentials class==>POJO class
 ===in the @Test
 create the object of credentials class using parameterized constructor
 and use the obj reference in the .body()
 But
 //pojo to json: serialization: ObjectMapper(Jackson lib is needed)
 google search 'jackson maven api'
 ===>databind core--coming from fasterxml.jackson.core
 <dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-core</artifactId>
			<version>2.17.2</version>
		</dependency>
 ---Add the Jackson Core lib in the pom.xml so the POJO gets picked up
 //json --> pojo: De-serialization CONCEPT***

 jackson-databind works************


HOW TO CREATE POJO WITH A LOMBOK:
WHAT IS LOMBOK??--is an other 3rd party api which helps to maintain the POJO classes--you do not need to write such lengthy code -- using Encapsulation's Getters & 
it just says give me the blue print of the class with no.of vars and i will take care of everything--it provides n no of annotations with the @

------------------------------Creating another example using different URL(gorest.co.in) --GoRestapi --POST CAll and writing the RA code

user.json --created and the tc executed changing email id each time to pass the testcase

To fix this problem --in sel framework --we used to write a separate utility to generate Random email each time
Here in the API testing frame work--this problem is handled using the POJO class
----IN THE POJO Classes-will create something like user.java--and using the setter method-will set the email dynamically- so as to generate fresh email eachtime
---this is a dynamic json body we required for this POsT call--since email field needs to be different each time
-----------------------now creating new tc's using restful-booker API and creating a work flow with
https://restful-booker.herokuapp.com/auth
Booking
===========
GetBookingIds
GetBooking
CreateBooking
UpdateBooking
PartialUpdateBooking
DeleteBooking
============
CRUD: 

test1:
test: create booking(POST)
post: booking id -123
get: /booking/123: 200

test2:
update a booking:
post: bookingid - 123
get: /booking/123: 200
put: /booking/123: 200

put: /booking/989: 200
QA: 989
STAGE: 989
UAT: 989
PROD: 989
-------------------Right approach to create the tc for UPDATE--is always to create a fresh bookingid and use the same booking id in the PUT call
this approach will work in all the environments
Since in any environment -- it will always create the fresh booking id and then append in the GET CALL and PUT Call
---works in any environment--is the right to test all the CRUD operations
NEVER DEPEND ON THE EXISTING USERS==because we never know the user who is available today --will be available tomorrow or NOT- 
----So we have to create our own DATA dynamically thru the script and use it each time

test3:
delete a booking:
post: booking id - 123
get: /booking/123: 200
delete: /booking/123: 200/401/204

test4:
get a booking
post: bookingid -123
get: /booking/123: 200

=============> tcs should be independent to each other--all tests1,2,3and 4 are

t1:
update a booking:
post: bookingid - 123
get: /booking/123: 200
put: /booking/123" 200

t1:
post: bookingid -123
get: /booking/123: 200

t2:(dependsOnMethods = t1)
put: /booking/{userid}: 200

IQ] HOW WILL YOU DESIGN YOUR TC'S??
===========================All the tc's which we write should follow the AAA pattern---Arrange, Act, Assert

Arrange the steps properly-tcase steps
Act-do the action
Assert-do the validations

AAA
=======is a very popular pattern used by DEVs as well for writing unit level test cases
arrange the test steps, act it and assert it******************
=======even in the api testing -- same pattern must be followed
all the tc's should have its own pre-conditions, post conditions etc
==============it is ok if it each tc has many steps==> repetitive--will optimize with utility classes==> generic methods/classes
BUT THERE SHOULD NOT BE ANY DEPENDENCY BETWEEN TWO TC'S

HW] with the gorest.co.in --do the same thing get all user ids????????????????????????????
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}
