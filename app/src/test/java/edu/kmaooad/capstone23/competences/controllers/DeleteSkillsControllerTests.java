package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.competences.dal.MongoSkillsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteSkillsControllerTests {
    @Inject
    private MongoSkillsRepository repository;

    ObjectId createTestSkill(String name, ObjectId parent) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillName", name);
        if (parent != null) {
            jsonAsMap.put("parentSkill", parent.toHexString());
        }

        String objectId = given()
            .contentType("application/json")
            .body(jsonAsMap)
            .when()
            .post("/skills/create")
            .then()
            .statusCode(200)
            .extract()
            .path("skill");

        return new ObjectId(objectId);
    }

    @Test
    @DisplayName("Delete Skill: Basic")
    public void testBasicSkillDeletion() {
        ObjectId skillToDelete = createTestSkill("food", null);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", skillToDelete.toHexString());

        // works normally
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skills/delete")
                .then()
                .statusCode(200);

        // the skill is deleted, so an error is raised
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skills/delete")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Delete Skill: With children")
    public void testSkillDeletionWithChildren() {        
        ObjectId parentSkill = createTestSkill("creep walk", null);
        // create a child skill
        createTestSkill("hustlin", parentSkill);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", parentSkill.toHexString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skills/delete")
                .then()
                .statusCode(400);
    }
}
