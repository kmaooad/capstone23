package edu.kmaooad.capstone23.competences.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateSkillControllerTests {

    @Test
    @DisplayName("Update Skill: Basic")
    public void testBasicOrgCreation() {
        var skillId = createSkill();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", skillId);
        jsonAsMap.put("skillName", "drinks");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skills/update")
                .then()
                .statusCode(200);
    }

    private String createSkill() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillName", "food");

        return given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skills/create")
                .then()
                .statusCode(200)
                .extract()
                .path("skill");
    }
}
