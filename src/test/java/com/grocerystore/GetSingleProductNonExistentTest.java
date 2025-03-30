package com.grocerystore;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetSingleProductNonExistentTest extends BaseTest {

    @Test
    public void requestInvalidProductShouldReturn404() {
        Response response = given()
            .header("Authorization", "Bearer " + accessToken)
            .get("/products/9999999999");

        assertEquals(response.statusCode(), 404);
    }
}
