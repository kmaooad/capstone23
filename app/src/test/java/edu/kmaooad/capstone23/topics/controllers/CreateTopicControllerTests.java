package edu.kmaooad.capstone23.topics.controllers;

import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class CreateTopicControllerTests {

    @Inject
    TopicRepository topicRepository;

    @Test
    @DisplayName("Create Topic: Basic")
    public void testBasicTopicCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "New Topic");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/create")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Create Topic: With Parent Topic")
    public void testCreateWithParentTopic() {
        Topic parentTopic = new Topic();
        parentTopic.name = "Parent Topic";
        topicRepository.insert(parentTopic);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Child Topic");
        jsonAsMap.put("parentId", parentTopic.id.toHexString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/create")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Create Topic: Invalid Parent Topic")
    public void testCreateWithInvalidParentTopic() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Child Topic");
        jsonAsMap.put("parentId", "nonExistentParentId");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/create")
                .then()
                .statusCode(400);
    }


    @Test
    @DisplayName("Create Topic: Invalid Topic")
    public void testCreateWithInvalidTopic() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "i");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/create")
                .then()
                .statusCode(400);
    }
}
