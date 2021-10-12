package day2;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class API3Negative {
  @Test()
  public void recordNotFound() {
	           given()
			  .when()
			   .get("http://3.13.86.142:3000/contacts/5")
			  .then()
			  .log()
			  .body()
			  .statusCode(404);
			  
  }
  
  @Test(description="Adding Contact with null object")
  public void addContact1() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  loc.put("city","Pune");
	  loc.put("country","India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "");
	  details.put("lastName","Jhonson");
	  details.put("email","Elsa@gmail.com");
	  details.put("location",loc);
	  details.put("employer",emp);
	   
	 String error= given()
	        .header("Content-Type","application/json")
	        .body(details.toJSONString())
             .when()
	             .post("http://3.13.86.142:3000/contacts")
	        .then()
	              .log()
	             .body()
	             .statusCode(400)
	             .extract()
	             .path("err");
	Assert.assertTrue(error.contains("firstName: First Name is required"));
}
  
  @Test(description="Add a contact with to many charactors")
  public void addContact2() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  loc.put("city","PunePunePunePunePunePunePunePunePunePune");
	  loc.put("country","India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Elsa");
	  details.put("lastName","Jhonson");
	  details.put("email","Elsa@gmail.com");
	  details.put("location",loc);
	  details.put("employer",emp);
	   
	         String error=given()
	        .header("Content-Type","application/json")
	        .body(details.toJSONString())
             .when()
	             .post("http://3.13.86.142:3000/contacts")
	        .then()
	              .log()
	             .body()
	             .statusCode(400)
	             .extract()
	             .path("err");
	         Assert.assertTrue(error.contains("is longer than the maximum allowed length"));
	            
} 
 
  @Test(description="Add a contact with to invalid charactors")
  public void addContact3() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  loc.put("city","Pune");
	  loc.put("country","India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "12234");
	  details.put("lastName","Jhonson");
	  details.put("email","Elsa@gmail.com");
	  details.put("location",loc);
	  details.put("employer",emp);
	   
	      String error=  given()
	        .header("Content-Type","application/json")
	        .body(details.toJSONString())
             .when()
	             .post("http://3.13.86.142:3000/contacts")
	        .then()
	              .log()
	             .body()
	             .statusCode(400)
	             .extract()
	             .path("err");
	         Assert.assertTrue(error.contains("Validator failed for path"));
	            
}
  @Test(description="Add a contact with to invalid email")
  public void addContact4() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  loc.put("city","Pune");
	  loc.put("country","India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Anna");
	  details.put("lastName","Jhonson");
	  details.put("email","Elsamailcom");
	  details.put("location",loc);
	  details.put("employer",emp);
	   
	String error=  given()
	        .header("Content-Type","application/json")
	        .body(details.toJSONString())
             .when()
	             .post("http://3.13.86.142:3000/contacts")
	        .then()
	              .log()
	             .body()
	             .statusCode(400)
	             .extract()
	             .path("err");
	        Assert.assertTrue(error.contains("Validator failed for path"));
	            
}

}
