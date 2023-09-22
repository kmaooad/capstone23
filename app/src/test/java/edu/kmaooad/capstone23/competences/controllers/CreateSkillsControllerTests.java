package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateSkillsControllerTests {

    @BeforeAll
    static void deleteAllData() {
        SkillsRepository skillsRepository = new SkillsRepository();
        skillsRepository.deleteAll();
    }


    @Test
    @DisplayName("Create Skill: Basic")
    public void testBasicOrgCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillName", "food");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skills/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Skill Relation: Basic")
    public void testSkillRelationCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillName", "food");

        String result = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skills/create")
                .then()
                .statusCode(200)
                .extract()
                .path("skill");

        Map<String, Object> jsonAsMap2 = new HashMap<>();
        jsonAsMap2.put("skillName", "fruits");
        jsonAsMap2.put("parentSkill", result);

        given()
                .contentType("application/json")
                .body(jsonAsMap2)
                .when()
                .post("/skills/create")
                .then()
                .statusCode(200)
        ;

    }

    @Test
    @DisplayName("Create Skill Relation: Basic")
    public void testSkillBadRelationCreation() {

        Map<String, Object> jsonAsMap2 = new HashMap<>();
        jsonAsMap2.put("skillName", "fruits");
        jsonAsMap2.put("parentSkill", new ObjectId().toHexString());

        given()
                .contentType("application/json")
                .body(jsonAsMap2)
                .when()
                .post("/skills/create")
                .then()
                .statusCode(400)
        ;
    }
}
