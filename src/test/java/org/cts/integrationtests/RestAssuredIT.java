package org.cts.integrationtests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.cts.model.StoredValue;
import org.cts.service.StoreRetreiveService;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by dharma on 5/28/18.
 * updated by Adi on 06/08/19.
 */

public abstract class RestAssuredIT implements StoreRetreiveService {
	  public static final org.slf4j.Logger logger = LoggerFactory.getLogger(RestAssuredIT.class);
	  private List<StoredValue> storedValues;
	  
    @Before
    public void setUpTest() {
    	 RestAssured.baseURI = "http://localhost:8080/api";
    }
     
    //1. listAllVarValue - Negative
    @Test
    public void testlistAllVarValue_negative() {
    	try {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.accept(ContentType.JSON).get("/retrivestoredval/");
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(204));
        logger.info("RestAssuredIT::"+"Method Name :: testRetriveStoredValUp::"+"testlistAllVarValue"+ "HttpStatus.NO_CONTENT(204)::"+ "Validation is Succesful");
    	}
    	catch (Exception e)
    	{
    		logger.error("Error in Class - "+"RestAssuredIT::"+"Method Name :: testlistAllVarValue_negative::"+ e.getMessage());
    	
    	}
    }
    
    //2. listAllVarValue
    @Test
    public void testlistAllVarValue() {
    	try {
    		
    	storedValues = new ArrayList<StoredValue>();
        StoredValue first = new StoredValue("xyz", "2345");
        this.saveVarValue(first);
             
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.accept(ContentType.JSON).get("/retrivestoredval/");
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
        logger.info("RestAssuredIT::"+"Method Name :: testlistAllVarValue::Expected Status-HttpStatus.NO_CONTENT(200)::validation is Succesful");
    	}
    	catch (Exception e)
    	{
    		logger.error("Error in Class - "+"RestAssuredIT::"+"Method Name :: testlistAllVarValue::"+ e.getMessage());
    	
    	}
    }
    
    //2.getVarValue - Negative
    @Test
    public void testretreiveByVarName_negative() {
    	try {
        RequestSpecification httpRequest = RestAssured.given();
        Response response =  httpRequest.accept(ContentType.JSON).param("variablename","abc").get("/retrivestoredval/{variablename}");
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(404));
        logger.info("RestAssuredIT::"+"Method Name :: testretreiveByVarName_negative::HttpStatus.NOT_FOUND(404)::Nagative validation completed Succesfully");
    	}
    	catch (Exception e)
    	{
    		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testretreiveByVarName_negative::"+ e.getMessage());
    	}
    }
    
    //3.getVarValue
    @Test
    public void testretreiveByVarName() {
    	try {
    	
    	storedValues = new ArrayList<StoredValue>();
        StoredValue first = new StoredValue("pqr", "2345");
        this.saveVarValue(first);
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.accept(ContentType.JSON).param("variablename","pqr").get("/retrivestoredval/{variablename}");
        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
        logger.info("RestAssuredIT::"+"Method Name :: testretreiveByVarName::HttpStatus.OK(200)::validation completed Succesfully");
    	}
    	catch (Exception e)
    	{
    		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testretreiveByVarName::"+ e.getMessage());
    	}
    }
    
  //4. createVarValue - Negative
   @Test
   public void testcreateVarValue_varExists()  throws JSONException,InterruptedException {
   	try {
   	   	 String apiBody = "{\"xyz\":\"2345\"}";
   		 RequestSpecBuilder builder = new RequestSpecBuilder();
   		 builder.setBody(apiBody);
   		 builder.setContentType("application/json; charset=UTF-8");
   		 RequestSpecification httpRequest = builder.build();
   		 Response response = given().spec(httpRequest).when().post("/storevalue/");
   		 Assert.assertThat(response.getStatusCode(), Matchers.equalTo(409));
   	     logger.info("RestAssuredIT::"+"Method Name :: testcreateVarValue_varExists::HttpStatus.CONFLICT (409)::No new resource been Created");
   	     
 	}
     catch (Exception e)
 	{
 		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testcreateVarValue_varExists::"+ e.getMessage());
 	}
 
   }

  //  5.createVarValue
    @Test
    public void testcreateVarValue()  throws JSONException,InterruptedException {
    	try {
    		
    	     String apiBody = "{\"lmn\":\"2345\"}";
    	     RequestSpecBuilder builder = new RequestSpecBuilder();
    	     builder.setBody(apiBody);
    		 builder.setContentType("application/json; charset=UTF-8");
    		 RequestSpecification  httpRequest = builder.build();
    		 Response response = given().spec(httpRequest).when().post("/storevalue/");
    		 Assert.assertThat(response.getStatusCode(), Matchers.equalTo(201));
    	     logger.info("RestAssuredIT::"+"Method Name :: testcreateVarValue::HttpStatus.CREATED(201)::new resource has been Created");
    		 
  	}
      catch (Exception e)
  	{
  		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testcreateVarValue::"+ e.getMessage());
  	}
  
    }
    
    //6. updateVarValue - Negative
    
    @Test
    public void testupdateVarValue_negative()  throws JSONException,InterruptedException {
    	try {
    		//case -1 Duplicate resource - varExists
    		 String apiBody = "{\"jio\":\"2345\"}";
    		 RequestSpecBuilder builder = new RequestSpecBuilder();
    		 builder.setBody(apiBody);
    		 builder.setContentType("application/json; charset=UTF-8");
    		 RequestSpecification httpRequest = builder.build();
    		 Response response = given().spec(httpRequest).param("variablename","rjk").when().put("storevalue/{variablename}");
    		 Assert.assertThat(response.getStatusCode(), Matchers.equalTo(409));
    	     logger.info("RestAssuredIT::"+"Method Name :: testupdateVarValue_negative::HttpStatus.NOT_FOUND (404)::No resource could be found for updating");
    	     
  	}
      catch (Exception e)
  	{
  		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testupdateVarValue_negative::"+ e.getMessage());
  	}
  
    }

   //7.updateVarValue
     
     @Test
     public void testupdateVarValue()  throws JSONException,InterruptedException {
     	try {
     		
     	     String apiBody = "{\"xyz\":\"2345\"}";
     	     RequestSpecBuilder builder = new RequestSpecBuilder();
     	     builder.setBody(apiBody);
     		 builder.setContentType("application/json; charset=UTF-8");
     		 RequestSpecification  httpRequest = builder.build();
     		 Response response = given().spec(httpRequest).param("variablename","xyz").when().put("storevalue/{variablename}");
     		 Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
     	     logger.info("RestAssuredIT::"+"Method Name :: testupdateVarValue::HttpStatus.OK(200)::resource has been Updated");
     		 
   	}
       catch (Exception e)
   	{
   		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testupdateVarValue::"+ e.getMessage());
   	}
   
     }
     
    //8.deleteAllVarsVals
     
     public void testdeleteAllVarsVals() {
      	try {
      		
      		 RequestSpecification httpRequest = RestAssured.given();
             Response response = httpRequest.accept(ContentType.JSON).delete("/retrivestoredval/");
             Assert.assertThat(response.getStatusCode(), Matchers.equalTo(204));
             logger.info("RestAssuredIT::"+"Method Name :: testdeleteAllVarsVals::HttpStatus.NO_CONTENT(204)::"+ "Validation is Succesful");
      		 
    	}
        catch (Exception e)
    	{
    		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testdeleteAllVarsVals::"+ e.getMessage());
    	}
    
      }
     
 
    
}
