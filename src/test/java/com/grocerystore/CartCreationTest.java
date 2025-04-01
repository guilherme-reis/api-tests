package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartCreationTest extends BaseTest {

    @Test
    public void createCartShouldReturn201() {
        String payload = """
            {
              "productId": 4643,
              "quantity": 2
            }
        """;

        Response response = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body(payload)
            .post("/carts");

        assertEquals(response.getStatusCode(), 201);
    }
}
