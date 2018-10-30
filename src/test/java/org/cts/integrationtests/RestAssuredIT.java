package org.cts.integrationtests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.get;
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
        Response response = httpRequest.get("https://store-retreive.cfapps.io/api/retrivestoredval/");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void testRetriveStoredValTestDataValidation() {
        get("https://store-retreive.cfapps.io/api/retrivestoredval/").then().body("varname", equalTo(Arrays.asList("xyz")));
    }
}
