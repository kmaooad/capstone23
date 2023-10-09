package edu.kmaooad.capstone23.competences.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateSkillSetControllerTests {

    @Test
    @DisplayName("Create SkillSet: Basic")
    public void testBasicSkillSetCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillSetName", "SoftSkills");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create SkillSet: Name validation")
    public void testSkillSetCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillSetName", "SoftSkills_1");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/create")
                .then()
                .statusCode(400);
    }
}