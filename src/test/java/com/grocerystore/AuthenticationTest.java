package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class AuthenticationTest {

    public static String accessToken;

    @BeforeClass
    public void createAccessToken() {
        RestAssured.baseURI = "https://simple-grocery-store-api.glitch.me";

        String randomEmail = "user+" + UUID.randomUUID() + "@example.com";

        String body = """
            {
              "clientName": "Guilherme",
              "clientEmail": "%s"
            }
            """.formatted(randomEmail);

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/api-clients");

        assertEquals(response.getStatusCode(), 201);
        accessToken = response.jsonPath().getString("accessToken");
        assertNotNull(accessToken);
    }

    @Test
    public void verifyAccessTokenIsNotNull() {
        assertNotNull(accessToken);
    }
}
