package com.grocerystore;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class InvalidEndpointTest extends BaseTest {

    @Test
    public void requestInvalidEndpointShouldReturn404() {
        Response response = given()
            .header("Authorization", "Bearer " + accessToken)
            .get("/invalid-endpoint-here");

        assertEquals(response.statusCode(), 404);
    }
}
