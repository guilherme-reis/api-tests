package com.grocerystore;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddProductToCartNegativeTest extends BaseTest {

    private String cartId;

    @BeforeClass
    public void ensureCartExists() {
        cartId = System.getProperty("cartId");
        if (cartId == null) {
            Response createResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .post("/carts");
            Assert.assertEquals(createResponse.statusCode(), 201);
            cartId = createResponse.jsonPath().getString("cartId");
            Assert.assertNotNull(cartId);
            System.setProperty("cartId", cartId);
        }
    }

    @Test
    public void addProductToCartNegativeQuantityShouldReturn201() {
        Response response = given()
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body("{\"productId\":4643,\"quantity\":-1}")
            .post("/carts/" + cartId + "/items");
        Assert.assertEquals(response.statusCode(), 201);
    }
}
