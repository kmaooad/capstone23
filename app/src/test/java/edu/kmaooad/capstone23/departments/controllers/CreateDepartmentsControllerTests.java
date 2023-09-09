package edu.kmaooad.capstone23.departments.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateDepartmentsControllerTests {

    @Test
    @DisplayName("Create Departments: Basic")
    public void testBasicOrgCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "FI");
        jsonAsMap.put("description", "Faculty of Informatics");
        jsonAsMap.put("parent", "KMA");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Departments: Name validation")
    public void testOrgCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Kma0oad_2023");
        jsonAsMap.put("description", "Faculty of Informatics");
        jsonAsMap.put("parent", "KMA");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/create")
                .then()
                .statusCode(400);
    }
}