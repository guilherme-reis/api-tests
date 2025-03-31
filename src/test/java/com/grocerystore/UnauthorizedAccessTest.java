package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UnauthorizedAccessTest extends BaseTest {

    @Test
    public void unauthorizedAccessShouldReturn401() {
        Response response = RestAssured
            .given()
            .baseUri(baseUri)
            .get("/cart/invalid-id/items");

        assertEquals(response.getStatusCode(), 404);
    }
}
