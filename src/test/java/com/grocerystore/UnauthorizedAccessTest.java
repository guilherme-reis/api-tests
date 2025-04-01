package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UnauthorizedAccessTest extends BaseTest {

    @Test
    public void accessProtectedEndpointWithoutTokenShouldReturn404() {
        Response response = RestAssured
            .given()
            .get("/carts");
    
        assertEquals(response.getStatusCode(), 404);
    }
    
    
}
