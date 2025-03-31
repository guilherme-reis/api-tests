package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RemoveNonExistentItemTest extends BaseTest {
    @Test
    public void removeNonExistentItemShouldReturn404() {
        Response createCartResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts");
        assertEquals(createCartResponse.statusCode(), 201);
        String cartId = createCartResponse.jsonPath().getString("cartId");
        Response response = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .delete("/carts/" + cartId + "/items/999999");
        assertEquals(response.statusCode(), 404);
    }
}
