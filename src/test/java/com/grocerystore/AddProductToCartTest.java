package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class AddProductToCartTest extends BaseTest {

    @Test
    public void addProductToCartShouldSucceed() {
        String cartId = System.getProperty("cartId");

        String requestBody = """
            {
                "productId": 4642,
                "quantity": 1
            }
        """;

        Response response = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .header("Content-Type", "application/json")
            .body(requestBody)
            .post("/carts/" + cartId + "/items");

        assertEquals(response.statusCode(), 201);
        assertNotNull(response.jsonPath().get("productId"));
        assertNotNull(response.jsonPath().get("quantity"));
    }
}
