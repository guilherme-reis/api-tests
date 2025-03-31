package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class AddProductToCartTest extends BaseTest {
    @Test(dependsOnMethods = "com.grocerystore.CartCreationTest.createCartShouldReturnCartId")
    public void addProductToCartShouldReturn200() {
        String cartId = System.getProperty("cartId");
        assertNotNull(cartId);
        Response response = RestAssured
            .given()
            .baseUri(baseUri)
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body("{\"productId\": 4643, \"quantity\": 1}")
            .post("/carts/" + cartId + "/items");
        assertEquals(response.statusCode(), 201);
    }
}
