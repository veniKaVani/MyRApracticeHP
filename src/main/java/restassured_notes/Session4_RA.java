package restassured_notes;

public class Session4_RA {
	
	/*
	 * 04_PostWithPOJO_PutGetChainWithPojo_SerializationWithJackson_Lombok_BuilderPattern
********************************************************************
AMONGST THE 3 WAYS OF SUPPLYING DATA TO THE REQUEST BODY-WHICH APPROACH IS BETTER??-1.hardcoded json in the body() as param 2. as a json file in
the body() 3. as a POJO class object reference inside the body()

1. is not suitable when the json is too big to be used as a param inside
the body()
2.is suitable only when the json is fixed/static--not suitable for dynamic data--the no of attributes and their values should never be changed
3. pojo is suitable for dynamic data

--CONVERTING pojo to json--->serialization
--json to pojo ---> De-serialization -- done using jackson-databind lib/gson provided by Microsoft/yasson lib etc...

so remove the previously added jackson-core lib from pom.xml then google
search 'jackson databind maven' and add this lib 
JACKSON DATABIND: lib will help do the Auto-serialization==>will automatically
convert the pojo reference added in the code to json
//pojo --> json: serialization
//json --> pojo: deserialization

//to take care of the above--jackson-databind --> is the most popular
library used--and is very easily parsed when used 

Supplying the Databody to the request/post call using POJO class becomes
headoc when the body to be supplied is huge--imagine dealing with 50 variables, and their getters and setters-becomes BOILER PLATE CODE
---to overcome this problem--the Lombok dependency/lib came into existence and also became significant as it solved the problem

LOMBOK:
---------------is a 3rd party api which has to be added in pom.xml file
it provides some annotations--
--is a very popular lib used in spring boot as well
--it just takes the blueprint of the class and then it takes care of
everything--creates the getters, setters
---------------------Nav notes
Lombok -- pom.xml

annotations: @

1.add pom.xml  //remove <scope>provided</scope> from lombok dependency
2.install a plugin  ==> download the .jar file and run the cmd LIKE
for jenkins

eclipse
lombok.jar
CMD: java -jar lombok.jar


Lombok installation:
-------------------google search 'maven lombok dependency'
Go to the first link and take the latest dependency and paste in pom.xml
remove the <scope>provided<scope>
===>you want to run it anywhere
then click on jar that you see on maven page
----the jar gets downloaded into your downloads--open the cmd here-
--dir --to see the lombok file-and copy the name with version
--RUN the cmd java -jar lombok-version.jar
--a pop up window shows up -hit the installBtn on it
THEN RESTART ECLIPSE and go to about eclipse--scroll down to see the 
mesge that lombok is installed
--------------------------------------DONE

HOW TO CREATE LOMBOK FROM JSON FILES IN THE PROJECT?? July2024apiPractice
-------------------In order to create the lombok you have to create the 
annotations
-------------create a userLombok class 1.
and take all the private variables from POJO class 2.
----add all annotations above the lombok class 3.
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Builder
-------------------With the below code(minimal)--can achieve all the features-same as with POJO class==>BOILER PLATE CODE minimized with LOMBOK 

@Data
@AllArgsConstructor 
@NoArgsConstructor
@Builder  
public class LombokUser {
	
	//1.private variables:
		private String name;
		private String email;
		private String gender;
		private String status;

} 
-------------------------WHAT IS THE USE OF BUILDER PATTERN
@Builder
this annotation is used to apply the builder pattern
HOW TO WRITE THE BUILDER PATTERN USING @Builder???
----------------------popular pattern to use for creating/POSt calls
@Test
public void updateUserWith_Builder(){
	
RestAssured.baseURI = "https://gorest.co.in";

//creating user classs object using lombok class:
new LombokUser();



}

---------------------------------------@Data mouseOvered to see
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


Generates getters for all fields, a useful toString method, and hashCode and equals implementations that checkall non-transient fields. Will also generate setters for all non-final fields, as well as a constructor(except that no constructor will be generated if any explicitly written constructors already exist). 

Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode. 

	 * 
	 * 
	 * 
	 * 
	 */

}
