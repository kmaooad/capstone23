package edu.kmaooad.capstone23.competences.topics.controllers;

import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateTopicControllerTests {
    @BeforeAll
    static void deleteAllData() {
        TopicRepository repository = new TopicRepository();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Create Topic: Basic")
    public void testBasicTopicCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Arguvert");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Topic: Name Validation")
    public void testBasicTopicCreationNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Arg");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/create")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create Topic: Parent topic Validation")
    public void testBasicTopicCreationParentValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Arguvert");
        jsonAsMap.put("parentId", 1);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/create")
                .then()
                .statusCode(400);
    }
}