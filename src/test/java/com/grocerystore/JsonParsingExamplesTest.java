package com.grocerystore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class JsonParsingExamplesTest extends BaseTest {

    @Test
    public void parseJsonUsingMap() throws Exception {
        Response response = given().get("/products");
        assertEquals(response.statusCode(), 200);

        List<Map<String, Object>> products = response.jsonPath().getList(".");
        assertFalse(products.isEmpty());

        Object firstId = products.get(0).get("id");
        assertTrue(firstId instanceof Integer || firstId instanceof Number);
    }

    @Test
    public void parseJsonUsingJacksonTypeReference() throws Exception {
        Response response = given().get("/products");
        assertEquals(response.statusCode(), 200);

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> products = mapper.readValue(response.getBody().asString(), new TypeReference<>() {});

        assertFalse(products.isEmpty());
        assertTrue(products.get(0).containsKey("name"));
    }
}
