package com.example.apitest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"; // Replace with your API URL
    }

    @Test
    public void testGetUsers() {
        given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].id", notNullValue());
    }

    @Test
    public void testPostCreation() {
        given()
            .header("Content-Type", "application/json")
            .body("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }")
            .when()
            .post("/posts")
            .then()
            .statusCode(201)
            .body("id", notNullValue());
    }
}
