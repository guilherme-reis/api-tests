package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StatusTest {

    @Test
    public void checkStatusReturns200() {
        RestAssured.baseURI = "https://simple-grocery-store-api.glitch.me";
        Response response = RestAssured
                .given()
                .when()
                .get("/status");

        assertEquals(response.getStatusCode(), 200);
    }
}
