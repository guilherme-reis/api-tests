package com.grocerystore;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddProductToCartNegativeTest extends BaseTest {
    private String cartId;

    @BeforeClass
    public void createCart() {
        cartId = given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts")
            .jsonPath()
            .getString("cartId");
    }

    @Test
    public void addProductToCartNegativeQuantityShouldReturn400() {
        String requestBody = """
            {
              "productId": 4642,
              "quantity": -5
            }
            """;

        Response response = given()
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body(requestBody)
            .post("/carts/" + cartId + "/items");

        Assert.assertEquals(response.getStatusCode(), 400);
    }
}
