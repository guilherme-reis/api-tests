package com.grocerystore;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class StatusTest extends BaseTest {

    @Test
    public void checkStatusReturns200AndValidBody() {
        Response response = given().get("/status");

        assertEquals(response.statusCode(), 200);

        String status = response.jsonPath().getString("status");
        assertNotNull(status);
        assertEquals(status.toLowerCase(), "up");
    }
}
