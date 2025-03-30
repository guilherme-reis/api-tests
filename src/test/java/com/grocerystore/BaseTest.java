package com.grocerystore;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static String baseUri = "https://simple-grocery-store-api.glitch.me";
    protected static String accessToken;

    @BeforeClass
    public void authenticate() {
        accessToken = RestAssured
            .given()
            .baseUri(baseUri)
            .contentType("application/json")
            .body("{\"email\": \"user" + System.currentTimeMillis() + "@example.com\"}")
            .post("/auth/register")
            .jsonPath()
            .getString("accessToken");
    }
}
