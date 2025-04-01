package com.grocerystore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AdditionalBasicTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://simple-grocery-store-api.glitch.me";
    }

    @Test
public void getAllProductsShouldReturn200() {
    Response response = given().get("/products");
    Assert.assertEquals(response.getStatusCode(), 200);
}


    @Test
    public void getCartShouldReturn404() {
        Response response = given().get("/carts");
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void getProductByIdShouldReturnCorrectDataType() {
        Response response = given().get("/products");
        int id = response.jsonPath().getInt("[0].id");
        response = given().get("/products/" + id);
        int price = (int) Double.parseDouble(response.jsonPath().getString("price"));
        Assert.assertTrue(id > 0);
        Assert.assertTrue(price > 0);
    }

    @Test
    public void searchNonexistentProductShouldReturn400() {
        Response response = given()
                .queryParam("q", "")
                .get("/products/search");

        Assert.assertEquals(response.getStatusCode(), 400);
    }
    @Test
public void getClientCreationWithoutEmailShouldReturn400() {
    String body = """
        {
          "clientName": "Test User"
        }
        """;

    Response response = given()
        .contentType("application/json")
        .body(body)
        .when()
        .post("/api-clients");

    Assert.assertEquals(response.getStatusCode(), 400);
}

}
