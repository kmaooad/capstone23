package edu.kmaooad.capstone23.activities.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateActivityControllerTest {




    @Test
    @DisplayName("Create activity successful")
    public void testActivityCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Sport");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/extra/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create activity name validation")
    public void testEmptyActivityCreate() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/extra/create")
                .then()
                .statusCode(400);
    }
}
