package org.cts.integrationtests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.cts.controller.SRController;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by dharma on 5/28/18.
 * updated by Aditya on 04/08/19.
 */

public class RestAssuredIT {
	  public static final org.slf4j.Logger logger = LoggerFactory.getLogger(RestAssuredIT.class);
	  
    @Before
    public void setUpTest() {
        //RestAssured.baseURI = "https://store-retreive.cfapps.io/api";
    	RestAssured.baseURI = "http://localhost:8080/api";
    }
    
    //Work in Progress to fix the Test Case failures and Exceptions
  
    //1. GET
    @Test
    public void testRetriveStoredValUp() {
    	try {
        RequestSpecification httpRequest = RestAssured.given();
        // Make a GET request call directly by using RequestSpecification.get() method.
        // Make sure you specify the resource name.
        Response response = httpRequest.get("/retrivestoredval/");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    	}
    	catch (Exception e)
    	{
    		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testRetriveStoredValUp::"+ e.getMessage());
    	}
    }

    @Test
    public void testRetriveStoredValTestDataValidation() {
    	try {
    	 String value="string";
    	 int statusCode =get("/retrivestoredval/").then().body("value", equalTo(Arrays.asList(value))).statusCode(200).extract().response().statusCode();
    	  Assert.assertEquals(statusCode, 204);
    	} 
    	  catch (Exception e)
      	{
      		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testRetriveStoredValTestDataValidation::"+ e.getMessage());
      	}
    }
    
    @Test
    public void testRetriveStoredValTestDataValidation_badauthentication() {
    	try {
    	Map<String,String> variableValues = new HashMap<>();
    	variableValues.put("variable1", "value1");
    	variableValues.put("variable2", "value2");
    	int statusCode = given().auth().basic("badusername","badpassword").get("/retrivestoredval/").then().statusCode(404).extract().response().statusCode();
    	Assert.assertEquals(statusCode, 404);
    	}
    	catch (Exception e)
    	{
    		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testRetriveStoredValTestDataValidation_badauthentication::"+ e.getMessage());
    	}
    }
    
    @Test
    public void testRetriveStoredValTestDataValidation_negative() {
    	try {
    	Map<String,String> variableValues = new HashMap<>();
    	variableValues.put("variable1", "value1");
    	variableValues.put("variable2", "value2");
    	int statusCode = get("/retrivestoredval/").then().body("variable1", equalTo(Arrays.asList("test"))).statusCode(404).extract().response().statusCode();
    	Assert.assertEquals(statusCode, 404);
    	}
    	catch (Exception e)
    	{
    		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testRetriveStoredValTestDataValidation_variable::"+ e.getMessage());
    	}
    }
    
    @Test
    public void testRetriveStoredValTestDataValidation_variable() {
    	try {
    	String variable = "test";
        given().pathParam("variablename",variable).when().get("/retrivestoredval/{variablename}").then().assertThat().body("variablename",hasSize(1));
    	}
        catch (Exception e)
    	{
    		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testRetriveStoredValTestDataValidation_variable::"+ e.getMessage());
    	}
    }
    
    @Test
    public void testRetriveStoredValTestDataValidation_notfound() {
    	try {
    	String variable = "";
    	int statusCode=given().pathParam("variablename",variable).when().get("/retrivestoredval/{variablename}").then().statusCode(404).extract().response().statusCode();
        Assert.assertEquals(statusCode, 404);
    	}
        catch (Exception e)
    	{
    		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testRetriveStoredValTestDataValidation_notfound::"+ e.getMessage());
    	}
    }
    
    @Test
    public void testRetriveStoredVallistall() {
    	try {
    	Map<String,String> variableValues = new HashMap<>();
    	variableValues.put("variable1", "value1");
    	variableValues.put("variable2", "value2");
    	variableValues.put("variable3", "value3");
    	
    	 given().contentType("application/json").body(variableValues).when().get("/retrivestoredval/").then()
    	.body("variable1", equalTo("value1"))
        .body("variable2", equalTo("value2"))
    	.body("variable3", equalTo("value3"));
    	}
    	 catch (Exception e)
     	{
     		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testRetriveStoredVallistall::"+ e.getMessage());
     	}
    }
    
    //POST
    @Test
    public void testcreateVarValue_positive() {
    	try {
        Map<String,String> createvariable = new HashMap<>();
        createvariable.put("storedValue", "xyx1111");
        int statusCode=given().contentType("application/json").body(createvariable).when().post("/api/storevalue/").then().statusCode(201).extract().response().statusCode();
        Assert.assertEquals(statusCode, 201);
    	}
        catch (Exception e)
    	{
    		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testcreateVarValue_positive::"+ e.getMessage());
    	}
    }
    
    
    @Test
    public void testcreateVarValue_negative() {
    	try {
        Map<String,String> createvariable = new HashMap<>();
        createvariable.put("storedValue", "xyx1111");
        int statusCode=given().auth().basic("badusername","badpassword").contentType("application/json").body(createvariable).when().post("/api/storevalue/").then().statusCode(401).extract().response().statusCode();
        Assert.assertEquals(statusCode, 200);
    	}
        catch (Exception e)
    	{
    		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testcreateVarValue_negative::"+ e.getMessage());
    	}
    }
    
    @Test
    public void testcreateVarValue_negative_badauthentication() {  
    	try {
    	 int statusCode=given().auth().basic("badusername","badpassword").delete().then().statusCode(401).extract().response().statusCode();
    	 Assert.assertEquals(statusCode, 401);
    	}
    	 catch (Exception e)
     	{
     		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testcreateVarValue_negative_badauthentication::"+ e.getMessage());
     	}
    }
    

	//DELETE
    @Test
    public void testRetriveStoredValTestDeleteResource() {   	
    try {
    	 int statusCode=given().delete("/retrivestoredval/").then().statusCode(200).extract().response().statusCode();
    	 Assert.assertEquals(statusCode, 200);
    	}
    	 catch (Exception e)
     	{
     		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testRetriveStoredValTestDeleteResource_nocontent::"+ e.getMessage());
     	}
    }
    
    @Test
    public void testRetriveStoredValTestDeleteResource_nocontent() {
    	try {
    	 int statusCode=given().delete("/retrivestoredval/2").then().statusCode(204).extract().response().statusCode();
    	 Assert.assertEquals(statusCode, 204);
    	}
    	 catch (Exception e)
     	{
     		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testRetriveStoredValTestDeleteResource_nocontent::"+ e.getMessage());
     	}
    }
    
    @Test
    public void testRetriveStoredValTestDeleteResource_badauthentication() {   	
    	try {
    	 int statusCode=given().auth().basic("badusername","badpassword").delete().then().statusCode(401).extract().response().statusCode();
    	 Assert.assertEquals(statusCode, 401);
    	}
    	 catch (Exception e)
     	{
     		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testRetriveStoredValTestDeleteResource_badauthentication::"+ e.getMessage());
     	}
    }
    
    //PUT
    @Test
    public void testupdateVarValue_positive() {   
    	try {
    	 Map<String,String> storedValue = new HashMap<>();
    	 storedValue.put("variablename", "xyx1111");
    	 int statusCode=given().contentType("application/json").pathParam("variablename", "test").body(storedValue).when().put("/storevalue/{variablename}").then().statusCode(201).extract().response().statusCode();	
    	 Assert.assertEquals(statusCode, 201);
    	}
    	 catch (Exception e)
     	{
     		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testupdateVarValue_positive::"+ e.getMessage());
     	}
    }
    
    
    @Test
    public void testupdateVarValue_negative() { 
    	try {
    	 Map<String,String> storedValue = new HashMap<>();
    	 storedValue.put("variablename", "");
    	 int statusCode=given().contentType("application/json").pathParam("variablename", "test").body(storedValue).when().put("/storevalue/{variablename}").then().statusCode(404).extract().response().statusCode();	
    	 Assert.assertEquals(statusCode, 404);
    	}
    	 catch (Exception e)
     	{
     		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testupdateVarValue_negative::"+ e.getMessage());
     	}
    }
    
    
    @Test
    public void testupdateVarValue_negative_badauthentication() {  
    	try {
    	 Map<String,String> storedValue = new HashMap<>();
    	 storedValue.put("variablename", "xyx1111");
    	 int statusCode=given().auth().basic("badusername","badpassword").pathParam("variablename", "variablename").contentType("application/json").body(storedValue).when().put("/storevalue/{variablename}").then().statusCode(401).extract().response().statusCode();	
    	 Assert.assertEquals(statusCode, 401);
    	}
    	 catch (Exception e)
     	{
     		logger.info("Error in Class - "+"RestAssuredIT::"+"Method Name :: testupdateVarValue_negative_badauthentication::"+ e.getMessage());
     	}
    }
    
}
