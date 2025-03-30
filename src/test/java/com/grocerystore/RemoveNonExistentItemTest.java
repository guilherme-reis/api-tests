package com.grocerystore;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class RemoveNonExistentItemTest extends BaseTest {

    @Test
    public void removeNonExistentItemShouldReturn404() {
        Response createCart = given()
            .header("Authorization", "Bearer " + accessToken)
            .post("/carts");
        assertEquals(createCart.statusCode(), 201);
        String cartId = createCart.jsonPath().getString("cartId");
        assertNotNull(cartId);

        Response response = given()
            .header("Authorization", "Bearer " + accessToken)
            .delete("/carts/" + cartId + "/items/999999");
        assertEquals(response.statusCode(), 404);
    }
}
