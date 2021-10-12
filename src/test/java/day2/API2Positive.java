package day2;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import static io.restassured.RestAssured.*;
public class API2Positive {
	
	//https://documenter.getpostman.com/view/401288/SWLceUSf?version=latest
	String id;
  @Test(enabled=false,description="Getting all contact list")
  public void getContactListInfo() {
	  given()
	  .when()
	   .get("http://3.13.86.142:3000/contacts")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
	  }
  @Test(enabled=false,description="Getting specific contact ")
  public void getSpesificContact() {
	  Response res= given()
	  .when()
	   .get("http://3.13.86.142:3000/contacts/615db585f2967f0ec893abb3"); //add contact id to get specific contact
	  System.out.println(res.getTime());
	   
	  res.then() //cannot use then after response therefore we split it
	  .log()
	  .body()
	  .statusCode(200);
  } 
  @Test(enabled=true,description="Add a contact ")
  public void addContact() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  loc.put("city","Pune");
	  loc.put("country","India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Elsa");
	  details.put("lastName","Jhonson");
	  details.put("email","Elsa@gmail.com");
	  details.put("location",loc);
	  details.put("employer",emp);
	   
	 ExtractableResponse<Response> res= given()
	        .header("Content-Type","application/json")
	        .body(details.toJSONString())
             .when()
	             .post("http://3.13.86.142:3000/contacts")
	        .then()
	              .log()
	             .body()
	             .statusCode(200)
	             .extract();
	          //   .path("_id");
	         id=res.path("_id");
	 System.out.println(res.path("_id"));
	 System.out.println(res.path("firstName"));
	 System.out.println(res.path("location.city"));
}


@Test(enabled=true,dependsOnMethods="addContact",description="Modify a contact ")
public void modifyContact() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  loc.put("city","Pune");
	  loc.put("country","India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Anna");
	  details.put("lastName","Jhonson");
	  details.put("email","Elsa@gmail.com");
	  details.put("location",loc);
	  details.put("employer",emp);
	      given()
	        .header("Content-Type","application/json")
	        .body(details.toJSONString())
           .when()
	             .put("http://3.13.86.142:3000/contacts/"+id)
	        .then()
	              .log()
	             .body()
	             .statusCode(204);
	          
} 
@Test(enabled=true,dependsOnMethods="modifyContact",description="Getting specific contact ")
public void getSpecificContact2() {
	  Response res= given()
	  .when()
	   .get("http://3.13.86.142:3000/contacts/"+id); //add contact id to get specific contact
	  System.out.println(res.getTime());
	   
	  res.then() //cannot use then after response therefore we split it
	  .log()
	  .body()
	  .statusCode(200);
	  
}   
@Test(enabled=true,dependsOnMethods="getSpecificContact2",description="delete a contact ")
public void deleteContact() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  loc.put("city","Pune");
	  loc.put("country","India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Anna");
	  details.put("lastName","Jhonson");
	  details.put("email","Elsa@gmail.com");
	  details.put("location",loc);
	  details.put("employer",emp);
	      given()
	        .header("Content-Type","application/json")
	        .body(details.toJSONString())
           .when()
	             .delete("http://3.13.86.142:3000/contacts/"+id)
	        .then()
	              .log()
	             .body()
	             .statusCode(204);
	          
} 
  

  
}
