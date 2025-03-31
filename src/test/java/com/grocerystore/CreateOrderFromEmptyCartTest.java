package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CreateOrderFromEmptyCartTest extends BaseTest {
    @Test
    public void createOrderFromEmptyCartShouldReturn400() {
        Response createCartResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts");
        assertEquals(createCartResponse.statusCode(), 201);
        String cartId = createCartResponse.jsonPath().getString("cartId");
        Response orderResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/orders?cartId=" + cartId);
        assertEquals(orderResponse.statusCode(), 400);
    }
}
