package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RemoveNonExistentItemTest extends BaseTest {

    @Test
    public void removeNonExistentItemShouldReturn404() {
        Response createCartResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts");

        assertEquals(createCartResponse.statusCode(), 201, "Expected 201 when creating a cart");

        String cartId = createCartResponse.jsonPath().getString("cartId");
        assertNotNull(cartId, "Cart ID should not be null");

        Response response = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .delete("/carts/" + cartId + "/items/999999");

        assertEquals(response.statusCode(), 404, "Expected 404 when deleting non-existent item");

        String body = response.getBody().asString();
        assertNotNull(body, "Response body should not be null or empty");
        assertTrue(!body.isEmpty(), "Response body should not be empty");

        String error = response.jsonPath().getString("error");
        assertNotNull(error, "Error message should be present in the response");
        assertTrue(error.toLowerCase().contains("no item"), "Expected error to mention 'no item'");
    }
}
