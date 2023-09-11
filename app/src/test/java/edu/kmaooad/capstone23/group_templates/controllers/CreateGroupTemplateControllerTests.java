package edu.kmaooad.capstone23.group_templates.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateGroupTemplateControllerTests {

    @Test
    @DisplayName("Create Group Template: Basic")
    public void testBasicGroupTemplateCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "default_group");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/grouptemplates/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Group Template: Name Validation")
    public void testGroupTemplateCreationNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "d");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/grouptemplates/create")
                .then()
                .statusCode(400);
    }


}
