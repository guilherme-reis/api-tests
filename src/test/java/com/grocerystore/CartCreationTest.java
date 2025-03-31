package com.grocerystore;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CartCreationTest extends BaseTest {

    @Test
    public void createCartShouldReturnCartId() {
        Response response = given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts");

        assertEquals(response.statusCode(), 201);
        boolean created = response.jsonPath().getBoolean("created");
        String cartId = response.jsonPath().getString("cartId");

        assertTrue(created);
        assertNotNull(cartId);
        System.setProperty("cartId", cartId);
    }
}
