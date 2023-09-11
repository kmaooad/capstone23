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
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class UpdateSkillSetControllerTests {

    @Inject
    SkillSetRepository repository;
    private ObjectId idToUpdate;

    @BeforeEach
    void setUp() {
        SkillSet skillSet = new SkillSet();
        skillSet.name = "SoftSkills";
        repository.insert(skillSet);
        idToUpdate = skillSet.id;
    }

    @Test
    @DisplayName("Update SkillSet: Basic")
    public void testBasicSkillSetUpdate() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillSetId", idToUpdate.toString());
        jsonAsMap.put("skillSetName", "HardSkills");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/update")
                .then()
                .statusCode(200)
                .body("skillSetId", equalTo(idToUpdate.toString()))
                .body("skillSetName", equalTo("HardSkills"));
    }

    @Test
    @DisplayName("Update SkillSet: Name validation")
    public void testSkillSetUpdateWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillSetId", idToUpdate.toString());
        jsonAsMap.put("skillSetName", "HardSkills_1");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/update")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Update SkillSet: Non-existent skill set")
    public void testSkillSetUpdateWithNonExistendId() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillSetId", "abc");
        jsonAsMap.put("skillSetName", "HardSkills");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/update")
                .then()
                .statusCode(400);
    }

}

