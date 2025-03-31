package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddProductToCartNegativeTest {

    private String accessToken;
    private String cartId;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://simple-grocery-store-api.glitch.me";

        accessToken = given()
                .contentType("application/json")
                .body("""
                        {
                          "clientName": "Augusto",
                          "clientEmail": "guilherme601@hotmail.com"
                        }
                        """)
                .when()
                .post("/api-clients")
                .jsonPath()
                .getString("accessToken");

        cartId = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
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
                .when()
                .post("/carts/" + cartId + "/items");

        Assert.assertEquals(response.getStatusCode(), 400);
    }
}
