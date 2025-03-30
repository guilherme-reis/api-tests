package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class RemoveItemFromCartTest extends BaseTest {

    @Test
    public void removeItemFromCartShouldReturn204() {
        Response createCartResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts");

        assertEquals(createCartResponse.statusCode(), 201);
        String cartId = createCartResponse.jsonPath().getString("cartId");
        assertNotNull(cartId);

        Response productsResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .get("/products");

        assertEquals(productsResponse.statusCode(), 200);
        int productId = productsResponse.jsonPath().getInt("[0].id");

        JSONObject payload = new JSONObject();
        payload.put("productId", productId);
        payload.put("quantity", 1);

        Response addResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body(payload.toString())
            .post("/carts/" + cartId + "/items");

        assertEquals(addResponse.statusCode(), 201);
        String itemId = addResponse.jsonPath().getString("itemId");
        assertNotNull(itemId);

        Response deleteResponse = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .delete("/carts/" + cartId + "/items/" + itemId);

        assertEquals(deleteResponse.statusCode(), 204);
    }
}
