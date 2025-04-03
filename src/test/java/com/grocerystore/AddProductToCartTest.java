package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AddProductToCartTest extends BaseTest {

    @Test
    public void addProductToCartShouldReturn201() {
        String cartPayload = """
            {
              "productId": 4643,
              "quantity": 1
            }
        """;

        Response response = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body(cartPayload)
            .post("/carts");

        assertEquals(response.getStatusCode(), 201, "Expected status code 201");

        String cartId = response.jsonPath().getString("cartId");
        assertNotNull(cartId, "cartId should not be null");
        assertFalse(cartId.isBlank(), "cartId should not be blank");
    }
}
