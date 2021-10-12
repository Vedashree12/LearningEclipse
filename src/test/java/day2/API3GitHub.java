package day2;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.Header;

public class API3GitHub {
	@BeforeTest//Common authentication for both test
	public void beforeTest() {
		
		baseURI="https://api.github.com/user/repos";
		authentication=oauth2("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");
	}
	
	
  @Test(enabled=true,description="Get all Repositories")
  public void gettingAllRepository() {
	 given()
	  .when()
	    .get(baseURI)//even if we dont pass baseURI it takes automatically
	  .then()
	     .log()
	     .body()
	     .statusCode(200);
 }
  
  @Test(enabled=true,description="create Repository")
  public void createRepository() {
	  JSONObject data=new JSONObject();
	  data.put("name", "RestAssuredCreations1");
	  data.put("description","Created byRestAssured tool1");
	  data.put("homepage","http://api.com/eclipse1");
	 
	 given()
	  .body(data.toJSONString())
	 .when()
	     .post(baseURI)
	  .then()
	     .log()
	     .body()
	     .statusCode(201)
	     .time(Matchers.lessThan(1000L),TimeUnit.MILLISECONDS);//account should be created in less that 1000milsec
 }
  
  
  
  
  
  
  
  
  
}
