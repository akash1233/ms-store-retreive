package org.homedepot.integrationtests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import java.util.Arrays;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by dharma on 5/28/18.
 */
public class RestAssuredIT {
    @Before
    public void setUpTest() {
        RestAssured.baseURI = "https://store-retreive.cfapps.io/api";
    }

    @Test
    public void testRetriveStoredValUp() {
        RequestSpecification httpRequest = RestAssured.given();
        // Make a GET request call directly by using RequestSpecification.get() method.
        // Make sure you specify the resource name.
        Response response = httpRequest.get("/retrivestoredval/");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void testRetriveStoredValTestDataValidation() {
        get("/retrivestoredval/").then().body("varname", equalTo(Arrays.asList("xyz")));
    }
}
