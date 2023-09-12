package edu.kmaooad.capstone23.activities.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateExtracurricularActivityControllerTest {
    @Test
    @DisplayName("Create Extracurricular Activity: Basic")
    public void testBasicActivityCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("extracurricularActivityName", "Football");
        jsonAsMap.put("extracurricularActivityDate", new Date());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/extracurricularActivity/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Extracurricular Activity: Name validation")
    public void testActivityCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("extracurricularActivityName", "Fo0tball_2034");
        jsonAsMap.put("extracurricularActivityDate", new Date());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/extracurricularActivity/create")
                .then()
                .statusCode(400);
    }

}
