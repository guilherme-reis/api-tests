package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UpdateCartItemQuantityTest extends BaseTest {

    @Test
    public void updateCartItemQuantityShouldReturn204() {
        Response cartResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts");

        assertEquals(cartResponse.statusCode(), 201);
        String cartId = cartResponse.jsonPath().getString("cartId");
        assertNotNull(cartId);

        Map<String, Object> product = new HashMap<>();
        product.put("productId", "4643");
        product.put("quantity", 1);

        Response addResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body(product)
            .post("/carts/" + cartId + "/items");

        assertEquals(addResponse.statusCode(), 201);
        String itemId = addResponse.jsonPath().getString("itemId");
        assertNotNull(itemId);

        product.put("quantity", 2);

        Response updateResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body(product)
            .put("/carts/" + cartId + "/items/" + itemId);

        assertEquals(updateResponse.statusCode(), 204);
    }
}
