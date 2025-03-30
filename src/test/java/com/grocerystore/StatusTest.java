package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StatusTest extends BaseTest {

    @Test
    public void checkStatusReturns200() {
        Response response = RestAssured
            .given()
            .get("/status");

        assertEquals(response.statusCode(), 200);
    }
}
