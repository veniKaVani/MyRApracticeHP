package restassured_notes;

public class Session1_RA {
	
	/*
	 * RESTASSURED:
SESSION 1: RestAssuredIntro_MvnPrjSetUP_httpCallWithBDD_httpCallWithoutBDD
*************************************************************************
in previous classes-learnt many things in pm except in authorization part
---will comeback to pm again to understand various levels of authorization(1,2 and jwt and other level of authorization, api key as well), when we talk about with RA--then we can relate properly that how to test with pm and RA
BUT pm feature wise if u see-pm with newman and newman with jenkins, docker integration and all those things--HOW TO CREATE THE DOCKER IMAGE??
Also we have covered
we create the image and mount the image on the DockerHub-download that
image-run, create the container and run the tc's with the newman and pm
without the installation of newman in ur current system

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

FEATURE FILE: is the definition of the behaviour of the appn

IQ] WHAT IS THE INTERNAL ARCHITECTURE OF REST ASSURED???
On top of https- they created a restassured lib --which internally uses
http Client to call the api's(CRUD Operations) over the internet
restassured --is a domain specific language written in java on top of java's http
client

---->they created a wrapper on top of http client
---restassured doesnot have it's own client--it is using java jdk's http
client
--restassured cannot be used with java, ruby,python language

CoreFeaturesOfRestAssured:
--------------------
RestAssured: is a pure java based library
is open source, is a java DSL(domain specific lang)-it was difficult to write RestAssured code in java
--is a domain specific lang written in java on top of http client==> it is a wrapper on top of http client
--RestAssured is not having its own client, it is using java jdk client
CAN I SAY IF I CAN USE R_A WITH PYTHON?? NO Since it is a pure java based lang, cannot use with ruby, C# etc--they have their own rest assured
LIKE...

RestAssured, OKHttpClient, JerseyClient--are all wrappers on top of Java httpClient
JDK: every jdk provides a httpclient==>u can hit ur api calls over network -which java provides-But it is very complex to implement-as the methods are

soo they created a wrapper on top of http client called RestAssured Lib, which internally calls http client
the advantage of which is that they had given very straight forward methods like given, when,then, assert...
-can write proper testng code over here
-sel is also using java http client to use the browser-the advantage is that they gave simple given, when, then 
================================IN Postman the first thing we did was focus on Request and the Response
we have to validate-write assertions in the RESPONSE
1.Request
 --GET/POST/PUT/DELETE
 --URI: baseUrl +/endpoint +QueryParams
 --Headers:
 --Request Body/Payload
 --Auth: Oauth1,2,JWT,Bearer Token, API Key, Basic Auth


2.Response
 --Status
 --StatusLine
 --Body: JsonPath, xmlPath
 --Headers
 --Cookies
 ==========now we have to create simple mvn prj and add Rest Assured Lib
 Maven
 RestAssured Lib: pom.xml
 TestNG
===============================
Eclipse/IntelliJ
 we do not need to worry about parallel mode of exec for API testcases
 since api tcs always run sequentially and not parallel

 in the rest assured dependency

 <!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.5.0</version>
    <scope>test</scope>
</dependency>
----------------------scope>test</scope--is saying that ur  going to write code only in src/test/java folder
BUT here we are going to write in src/main/java as well so remove that line and save the prj 
--------------------in Rest Assured--2 ways to call to apis
1. BDD style
given
when
then

u have to write the code with given, when, then-also called builder pattern-which u can change the pattern as per the requirement
bdd style code we can write with the help of rest assured

whenever we r writing api code we have to create one api client in that 
specific lib as well--here we r using rest assured, which helps to call the api calls(crud)

//BDD style test: given -->when ---> then
DISADVANTAGE: of writing this BDD-builder pattern style of code-is that
you cannot write-Sys.out.println statement in between to print messages
on console--this can be over come with .log().all(); statements written
with given() when() then()---no need to integrate RA with logSL4J or
any other lib to print loggers
.log().all(); --> does the magic --designed only with given() when() then()

create a request
what is a static method==> can be called using the classname.methodname
--if u do not want to call using the classname-then do the static import
the particular class

static import does not come auto-type it manually
import static io.restassured.RestAssured.given;

//this given get that chaining is called builder //pattern
RestAssured.baseURI = "https://fakestoreapi.com";
           given()
		    .get("/products")
		      .then()
		        .assertThat()
		          .statusCode(200);

-------with the above code(builder pattern)--there is a problem that u 
cannot do console.log(); to print the mesge in between the code
------------> they found a soln for this problem as well -which is doing 
a log.all() --for all the gherkins keyword methods given() then() when()

HAMCREST LIBRARY: is a very popular lib used to write with matchers to match the actual vs expected results
is an in-built lib already available/added in RestAssured, with built in features/expressions-used to match the expected value 

every body()--has one matcher
#####################

RestAssured.baseURI = "https://fakestoreapi.com";
 given().log().all() //does magic - no need for log sl4j
    .get("/products")
		      .then().log().all()
		        .assertThat()
		          .statusCode(200);


---
OR |
---
direct as well


2. Non-BDD style


static:-------------
import static Employee.m1;
Employee class
{
	static m1(){};
	m2(){};
	main(){
	//Employee.m1();
	Employee obj = new Employee();
	obj.m2();
	}
}

Industry practice: 'https://fakestoreapi.com/products'

endpts : should start with /
=> get("/products") 

//
hamcrest lib: ----> RestAssured

hamcrest: this lib is already added in RestAssured internally
hamcrest is a popular lib for writing the matchers
===> if we really want to match the total size --in testng-we write assert.assertEquals(20)

every body method has matchers
//
hamcrest lib ---> RestAssured
Matchers
size -- 20

the response i get when i hit the above url 'https://fakestoreapi.com/products'

below in the response body the first line --100 can be represented as $
==> root of the body, top of the json, root node of json


----------------------------------------------response body of GET call
[
{
"id": 1,
"title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
"price": 109.95,
"description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
"category": "men's clothing",
"image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
"rating": {
"rate": 3.9,
"count": 120
}
},
{
"id": 2,
"title": "Mens Casual Premium Slim Fit T-Shirts ",
"price": 22.3,
"description": "Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
"category": "men's clothing",
"image": "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
"rating": {
"rate": 4.1,
"count": 259
}
},
{
"id": 3,
"title": "Mens Cotton Jacket",
"price": 55.99,
"description": "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
"category": "men's clothing",
"image": "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
"rating": {
"rate": 4.7,
"count": 500
}
},
{
"id": 4,
"title": "Mens Casual Slim Fit",
"price": 15.99,
"description": "The color could be slightly different between on the screen and in practice. / Please note that body builds vary by person, therefore, detailed size information should be reviewed below on the product description.",
"category": "men's clothing",
"image": "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg",
"rating": {
"rate": 2.1,
"count": 430
}
},
{
"id": 5,
"title": "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
"price": 695,
"description": "From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.",
"category": "jewelery",
"image": "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg",
"rating": {
"rate": 4.6,
"count": 400
}
},
{
"id": 6,
"title": "Solid Gold Petite Micropave ",
"price": 168,
"description": "Satisfaction Guaranteed. Return or exchange any order within 30 days.Designed and sold by Hafeez Center in the United States. Satisfaction Guaranteed. Return or exchange any order within 30 days.",
"category": "jewelery",
"image": "https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_.jpg",
"rating": {
"rate": 3.9,
"count": 70
}
},
{
"id": 7,
"title": "White Gold Plated Princess",
"price": 9.99,
"description": "Classic Created Wedding Engagement Solitaire Diamond Promise Ring for Her. Gifts to spoil your love more for Engagement, Wedding, Anniversary, Valentine's Day...",
"category": "jewelery",
"image": "https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg",
"rating": {
"rate": 3,
"count": 400
}
},
{
"id": 8,
"title": "Pierced Owl Rose Gold Plated Stainless Steel Double",
"price": 10.99,
"description": "Rose Gold Plated Double Flared Tunnel Plug Earrings. Made of 316L Stainless Steel",
"category": "jewelery",
"image": "https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_.jpg",
"rating": {
"rate": 1.9,
"count": 100
}
},
{
"id": 9,
"title": "WD 2TB Elements Portable External Hard Drive - USB 3.0 ",
"price": 64,
"description": "USB 3.0 and USB 2.0 Compatibility Fast data transfers Improve PC Performance High Capacity; Compatibility Formatted NTFS for Windows 10, Windows 8.1, Windows 7; Reformatting may be required for other operating systems; Compatibility may vary depending on user’s hardware configuration and operating system",
"category": "electronics",
"image": "https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg",
"rating": {
"rate": 3.3,
"count": 203
}
},
{
"id": 10,
"title": "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s",
"price": 109,
"description": "Easy upgrade for faster boot up, shutdown, application load and response (As compared to 5400 RPM SATA 2.5” hard drive; Based on published specifications and internal benchmarking tests using PCMark vantage scores) Boosts burst write performance, making it ideal for typical PC workloads The perfect balance of performance and reliability Read/write speeds of up to 535MB/s/450MB/s (Based on internal testing; Performance may vary depending upon drive capacity, host device, OS and application.)",
"category": "electronics",
"image": "https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg",
"rating": {
"rate": 2.9,
"count": 470
}
},
{
"id": 11,
"title": "Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5",
"price": 109,
"description": "3D NAND flash are applied to deliver high transfer speeds Remarkable transfer speeds that enable faster bootup and improved overall system performance. The advanced SLC Cache Technology allows performance boost and longer lifespan 7mm slim design suitable for Ultrabooks and Ultra-slim notebooks. Supports TRIM command, Garbage Collection technology, RAID, and ECC (Error Checking & Correction) to provide the optimized performance and enhanced reliability.",
"category": "electronics",
"image": "https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_.jpg",
"rating": {
"rate": 4.8,
"count": 319
}
},
{
"id": 12,
"title": "WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive",
"price": 114,
"description": "Expand your PS4 gaming experience, Play anywhere Fast and easy, setup Sleek design with high capacity, 3-year manufacturer's limited warranty",
"category": "electronics",
"image": "https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_.jpg",
"rating": {
"rate": 4.8,
"count": 400
}
},
{
"id": 13,
"title": "Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin",
"price": 599,
"description": "21. 5 inches Full HD (1920 x 1080) widescreen IPS display And Radeon free Sync technology. No compatibility for VESA Mount Refresh Rate: 75Hz - Using HDMI port Zero-frame design | ultra-thin | 4ms response time | IPS panel Aspect ratio - 16: 9. Color Supported - 16. 7 million colors. Brightness - 250 nit Tilt angle -5 degree to 15 degree. Horizontal viewing angle-178 degree. Vertical viewing angle-178 degree 75 hertz",
"category": "electronics",
"image": "https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg",
"rating": {
"rate": 2.9,
"count": 250
}
},
{
"id": 14,
"title": "Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultrawide Screen QLED ",
"price": 999.99,
"description": "49 INCH SUPER ULTRAWIDE 32:9 CURVED GAMING MONITOR with dual 27 inch screen side by side QUANTUM DOT (QLED) TECHNOLOGY, HDR support and factory calibration provides stunningly realistic and accurate color and contrast 144HZ HIGH REFRESH RATE and 1ms ultra fast response time work to eliminate motion blur, ghosting, and reduce input lag",
"category": "electronics",
"image": "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg",
"rating": {
"rate": 2.2,
"count": 140
}
},
{
"id": 15,
"title": "BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats",
"price": 56.99,
"description": "Note:The Jackets is US standard size, Please choose size as your usual wear Material: 100% Polyester; Detachable Liner Fabric: Warm Fleece. Detachable Functional Liner: Skin Friendly, Lightweigt and Warm.Stand Collar Liner jacket, keep you warm in cold weather. Zippered Pockets: 2 Zippered Hand Pockets, 2 Zippered Pockets on Chest (enough to keep cards or keys)and 1 Hidden Pocket Inside.Zippered Hand Pockets and Hidden Pocket keep your things secure. Humanized Design: Adjustable and Detachable Hood and Adjustable cuff to prevent the wind and water,for a comfortable fit. 3 in 1 Detachable Design provide more convenience, you can separate the coat and inner as needed, or wear it together. It is suitable for different season and help you adapt to different climates",
"category": "women's clothing",
"image": "https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_.jpg",
"rating": {
"rate": 2.6,
"count": 235
}
},
{
"id": 16,
"title": "Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket",
"price": 29.95,
"description": "100% POLYURETHANE(shell) 100% POLYESTER(lining) 75% POLYESTER 25% COTTON (SWEATER), Faux leather material for style and comfort / 2 pockets of front, 2-For-One Hooded denim style faux leather jacket, Button detail on waist / Detail stitching at sides, HAND WASH ONLY / DO NOT BLEACH / LINE DRY / DO NOT IRON",
"category": "women's clothing",
"image": "https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_.jpg",
"rating": {
"rate": 2.9,
"count": 340
}
},
{
"id": 17,
"title": "Rain Jacket Women Windbreaker Striped Climbing Raincoats",
"price": 39.99,
"description": "Lightweight perfet for trip or casual wear---Long sleeve with hooded, adjustable drawstring waist design. Button and zipper front closure raincoat, fully stripes Lined and The Raincoat has 2 side pockets are a good size to hold all kinds of things, it covers the hips, and the hood is generous but doesn't overdo it.Attached Cotton Lined Hood with Adjustable Drawstrings give it a real styled look.",
"category": "women's clothing",
"image": "https://fakestoreapi.com/img/71HblAHs5xL._AC_UY879_-2.jpg",
"rating": {
"rate": 3.8,
"count": 679
}
},
{
"id": 18,
"title": "MBJ Women's Solid Short Sleeve Boat Neck V ",
"price": 9.85,
"description": "95% RAYON 5% SPANDEX, Made in USA or Imported, Do Not Bleach, Lightweight fabric with great stretch for comfort, Ribbed on sleeves and neckline / Double stitching on bottom hem",
"category": "women's clothing",
"image": "https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_.jpg",
"rating": {
"rate": 4.7,
"count": 130
}
},
{
"id": 19,
"title": "Opna Women's Short Sleeve Moisture",
"price": 7.95,
"description": "100% Polyester, Machine wash, 100% cationic polyester interlock, Machine Wash & Pre Shrunk for a Great Fit, Lightweight, roomy and highly breathable with moisture wicking fabric which helps to keep moisture away, Soft Lightweight Fabric with comfortable V-neck collar and a slimmer fit, delivers a sleek, more feminine silhouette and Added Comfort",
"category": "women's clothing",
"image": "https://fakestoreapi.com/img/51eg55uWmdL._AC_UX679_.jpg",
"rating": {
"rate": 4.5,
"count": 146
}
},
{
"id": 20,
"title": "DANVOUY Womens T Shirt Casual Cotton Short",
"price": 12.99,
"description": "95%Cotton,5%Spandex, Features: Casual, Short Sleeve, Letter Print,V-Neck,Fashion Tees, The fabric is soft and has some stretch., Occasion: Casual/Office/Beach/School/Home/Street. Season: Spring,Summer,Autumn,Winter.",
"category": "women's clothing",
"image": "https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_.jpg",
"rating": {
"rate": 3.6,
"count": 145
}
}
]

---------------------------------------Notes again
Create one single prj for both-UI(Sel) automation + API(RA) for api automation--so whenever u want to launch the UI use the sel part of the
framework and run the TC's
And when u do not need UI part and using backend only--run the api test
cases which written in src/test/java

where exactly we have to work in the created Prj, src/main/java OR 
src/test/java???
there is no exact strict rule for now since right now we r not designing
the framework
----for running the api test cases we do not need the front end UI --so 
no need to launch browser etc....
--soo we can directly write the tc's with testng + rest assured here
------UNDER src/test/java --write tc's inside the pkges 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}
