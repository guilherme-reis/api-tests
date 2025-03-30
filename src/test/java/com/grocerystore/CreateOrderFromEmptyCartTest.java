package com.grocerystore;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateOrderFromEmptyCartTest extends BaseTest {

    @Test
    public void createOrderFromEmptyCartShouldReturn404() {
        Response cartResponse = given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts");
        assertEquals(cartResponse.statusCode(), 201);
        String cartId = cartResponse.jsonPath().getString("cartId");
        assertNotNull(cartId);

        Response orderResponse = given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/orders/" + cartId);

        assertEquals(orderResponse.statusCode(), 404);
    }
}
