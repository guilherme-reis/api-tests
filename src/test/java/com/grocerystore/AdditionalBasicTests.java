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
        Response response = given()
                .get("/products/4643");

        assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        String price = jsonPath.getString("price");
        assertNotNull(price);
        assertTrue(price.matches("\\d+\\.\\d{2}"));
    }

    @Test
    public void searchNonexistentProductShouldReturn404() {
        Response response = given()
                .queryParam("q", "nonexistentproduct123456789")
                .get("/search");

        assertEquals(response.getStatusCode(), 404);
    }

    @Test
public void searchProductsShouldReturn404() {
    Response response = given()
        .when()
        .get("/search");

    assertEquals(response.statusCode(), 404);
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

    @Test
public void postInvalidCartShouldReturn400() {
    Response response = given()
        .header("Authorization", "Bearer " + accessToken)
        .contentType("application/json")
        .body("{invalid: true}") 
        .when()
        .post("/carts");

    assertEquals(response.statusCode(), 400);
}

}
