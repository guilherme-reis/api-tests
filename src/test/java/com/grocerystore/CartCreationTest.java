package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CartCreationTest extends BaseTest {
    private String token;

    @BeforeClass
    public void setUp() {
        token = AuthenticationTest.accessToken;
    }

    @Test
    public void createCartShouldReturnCartId() {
        Response response = RestAssured
            .given()
            .header("Authorization", "Bearer " + token)
            .post("/carts");

        assertEquals(response.statusCode(), 201);
        String cartId = response.jsonPath().getString("cartId");
        assertNotNull(cartId);
        System.setProperty("cartId", cartId);
    }
}
