package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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

        assertEquals(response.getStatusCode(), 201);
    }
}
