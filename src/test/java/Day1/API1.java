package Day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class API1 {
	//https://openweathermap.org/current
  @Test(description="Getting whether information of specific city")
  public void getWeather() {
	  RestAssured.given() //precondition like authentication
	             .when()  //performs some steps
	             .get("https://api.openweathermap.org/data/2.5/weather?q=Pune&appid=144eb67271525775b880225d4cbae47a")
	             .then() //postconditions like verification
	             .log()  //print data in console
	             .body()
	             .statusCode(200);
	             
  }
  @Test(enabled=false,description="Getting whether information of specific city")
  public void getWeather2() {
	 Response res= RestAssured.given() //precondition like authentication
	             .when()  //performs some steps
	             .get("https://api.openweathermap.org/data/2.5/weather?q=Pune&appid=144eb67271525775b880225d4cbae47a");
	             System.out.println(res.prettyPrint());
	              System.out.println(res.getTime());
	              System.out.println(res.getStatusCode());
	              System.out.println(res.getContentType());
	              Assert.assertEquals(res.getStatusCode(),200);
	             
  }
  @Test(enabled=false,description="Getting whether information of specific city")
  public void getWeather3() {
	  RestAssured.given() //precondition like authentication
	             .queryParam("q", "Pune")
	             .queryParam("appid", "144eb67271525775b880225d4cbae47a")
	             .when()  //performs some steps
	             .get("https://api.openweathermap.org/data/2.5/weather")
	             .then() //postconditions like verification
	             .log()  //print data in console
	             .body()
	             .statusCode(200);
	             
  }
  @Test(description="Getting whether information of specific city")
  public void getWeather4() {
	  Map<String,String> param=new HashMap<String,String>();
	  param.put("q", "Pune");
	  param.put("appid", "144eb67271525775b880225d4cbae47a");
	  RestAssured.given() //precondition like authentication
	             .queryParams(param)
	             .when()  //performs some steps
	             .get("https://api.openweathermap.org/data/2.5/weather")
	             .then() //postconditions like verification
	             .log()  //print data in console
	             .body()
	             .statusCode(200);
	             
  }
}
