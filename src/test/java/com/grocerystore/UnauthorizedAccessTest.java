package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnauthorizedAccessTest extends BaseTest {

    @Test
    public void accessProtectedEndpointWithoutTokenShouldReturn404() {
        Response response = RestAssured
            .given()
            .get("/carts");

        assertEquals(response.getStatusCode(), 404, "Expected 404 Not Found when accessing protected endpoint without token");

        String responseBody = response.getBody().asString();
        assertNotNull(responseBody, "Response body should not be null");
        assertTrue(responseBody.contains("error") || responseBody.contains("not found"), "Response should contain error message");
    }
}
