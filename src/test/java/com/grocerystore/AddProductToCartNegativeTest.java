package com.grocerystore;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class AddProductToCartNegativeTest extends BaseTest {
    private String cartId;

    @BeforeClass
    public void createCart() {
        Response response = given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts");

        assertEquals(response.statusCode(), 201, "Cart creation should return 201");
        cartId = response.jsonPath().getString("cartId");
        assertNotNull(cartId, "cartId should not be null");
        assertFalse(cartId.isBlank(), "cartId should not be blank");
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

    assertEquals(response.getStatusCode(), 400, "Expected status code 400");

    String errorMessage = response.jsonPath().getString("error");
    System.out.println("Error message: " + errorMessage); // for debug
    assertNotNull(errorMessage, "Error message should not be null");

    boolean containsExpected = errorMessage.toLowerCase().matches(".*(quantity|invalid|negative).*");
    assertTrue(containsExpected, "Error message should mention 'quantity', 'invalid', or 'negative'");
}

}
