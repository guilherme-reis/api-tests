package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BaseTest {

    protected static final String BASE_URI = "https://simple-grocery-store-api.glitch.me";
    protected static String accessToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;

        if (accessToken == null) {
            String email = "user" + System.currentTimeMillis() + "@example.com";

            Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{\"clientName\": \"api-test\", \"clientEmail\": \"" + email + "\"}")
                .post("/api-clients");

            assertEquals(response.statusCode(), 201, "Expected status 201 when generating token");
            accessToken = response.jsonPath().getString("accessToken");
            assertNotNull(accessToken, "Access token should not be null");
        }
    }
}
