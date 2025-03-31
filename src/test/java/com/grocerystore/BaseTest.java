package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BaseTest {
    protected static final String baseUri = "https://simple-grocery-store-api.glitch.me";
    protected static String accessToken;

    @BeforeClass
    public void generateAccessToken() {
        if (accessToken == null) {
            String email = "user" + System.currentTimeMillis() + "@example.com";
            Response response = RestAssured
                .given()
                .baseUri(baseUri)
                .contentType("application/json")
                .body("{\"clientName\": \"api-test\", \"clientEmail\": \"" + email + "\"}")
                .post("/api-clients");
            assertEquals(response.statusCode(), 201);
            accessToken = response.jsonPath().getString("accessToken");
            assertNotNull(accessToken);
        }
        RestAssured.baseURI = baseUri;
    }
}
