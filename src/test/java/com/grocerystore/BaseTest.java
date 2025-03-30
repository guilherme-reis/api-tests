package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.util.UUID;

public class BaseTest {
    public static String accessToken;

    @BeforeClass
    public void baseSetup() {
        RestAssured.baseURI = "https://simple-grocery-store-api.glitch.me";

        if (accessToken == null) {
            String email = "user_" + UUID.randomUUID() + "@example.com";

            Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\"}")
                .post("/api-clients");

            accessToken = response.jsonPath().getString("accessToken");
        }
    }
}
