package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class GetCartContentsTest extends BaseTest {
    private String cartId;

    @BeforeClass
    public void ensureCartExists() {
        cartId = System.getProperty("cartId");
        if (cartId == null) {
            Response createResponse = RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .post("/carts");
            assertEquals(createResponse.statusCode(), 201);
            cartId = createResponse.jsonPath().getString("cartId");
            assertNotNull(cartId);
            System.setProperty("cartId", cartId);
        }
    }

    @Test
    public void getCartContentsShouldReturn200() {
        Response response = RestAssured
            .given()
            .header("Authorization", "Bearer " + accessToken)
            .get("/carts/" + cartId);
        assertEquals(response.statusCode(), 200);
        List<Map<String, Object>> items = response.jsonPath().getList("items");
        assertNotNull(items);
        assertTrue(items.size() >= 0);
    }
}
