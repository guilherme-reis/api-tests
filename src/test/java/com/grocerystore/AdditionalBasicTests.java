package com.grocerystore;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class AdditionalBasicTests extends BaseTest {

    @Test
    public void getProductByIdShouldReturnCorrectDataType() {
        Response response = given().get("/products/4643");

        assertEquals(response.statusCode(), 200, "Expected status code 200 for valid product ID");

        JsonPath json = response.jsonPath();
        String price = json.getString("price");
        String id = json.getString("id");
        String name = json.getString("name");

        assertNotNull(price, "Price should not be null");
        assertTrue(price.matches("\\d+\\.\\d{2}"), "Price format should be decimal with two digits");

        assertNotNull(id, "Product ID should not be null");
        assertNotNull(name, "Product name should not be null");
    }

    @Test
    public void searchNonexistentProductShouldReturn404() {
        Response response = given()
            .queryParam("q", "nonexistentproduct123456789")
            .get("/search");

        assertEquals(response.getStatusCode(), 404, "Expected 404 when searching nonexistent product");
        String body = response.getBody().asString();
        assertTrue(body.toLowerCase().contains("not found") || body.toLowerCase().contains("error"),
                "Response should contain an error message");
    }

    @Test
    public void searchProductsShouldReturn404WhenMissingQueryParam() {
        Response response = given().get("/search");

        assertEquals(response.statusCode(), 404, "Expected 404 when no query param is provided");
        String body = response.getBody().asString();
        assertTrue(body.toLowerCase().contains("not found") || body.toLowerCase().contains("error"),
                "Response should contain an error message");
    }


    @Test
    public void postInvalidCartShouldReturn400() {
        Response response = given()
            .header("Authorization", "Bearer " + accessToken)
            .contentType("application/json")
            .body("{invalid: true}")
            .post("/carts");

        assertEquals(response.statusCode(), 400, "Expected 400 for invalid cart body");

        String responseBody = response.getBody().asString();
        assertTrue(responseBody.toLowerCase().contains("error"), "Response should contain an error message");
    }
    @Test
    public void getAllProductsShouldReturnList() {
        Response response = given()
                .get("/products");

        assertEquals(response.statusCode(), 200);

        List<Map<String, Object>> products = response.jsonPath().getList(".");
        assertNotNull(products);
        assertTrue(products.size() > 0);
    }
}




    

    