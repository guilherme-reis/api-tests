package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CartCreationTest extends BaseTest {
    @Test
    public void createCartShouldReturnCartId() {
        Response response = RestAssured
            .given()
            .baseUri(baseUri)
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts");
        assertEquals(response.statusCode(), 201);
        String cartId = response.jsonPath().getString("cartId");
        assertNotNull(cartId);
        System.setProperty("cartId", cartId);
    }
}
