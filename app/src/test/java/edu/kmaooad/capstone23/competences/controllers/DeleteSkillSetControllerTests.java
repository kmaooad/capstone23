package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteSkillSetControllerTests {
    @Inject
    private SkillSetRepository repository;

    private ObjectId idToDelete;

    @BeforeEach
    void setUp() {
        SkillSet skillSet = new SkillSet();
        skillSet.name = "SoftSkills";
        repository.insert(skillSet);
        idToDelete = skillSet.id;
    }


    @Test
    @DisplayName("Delete Skill Set: Basic")
    public void testBasicSkillSetDeletion() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idToDelete.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/delete")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete Skill Set: Non-existent")
    public void testNonExistentSkillSetDeletion() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "ab12412");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/delete")
                .then()
                .statusCode(400);
    }

}
