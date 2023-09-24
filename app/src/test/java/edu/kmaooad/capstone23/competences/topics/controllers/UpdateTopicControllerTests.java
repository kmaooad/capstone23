package edu.kmaooad.capstone23.competences.topics.controllers;

import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@QuarkusTest
public class UpdateTopicControllerTests {

    private ObjectId idToUpdate;
    private ObjectId parentId;
    private ObjectId idWithParent;

    @Inject
    TopicRepository topicRepository;

    @BeforeEach
    void setUp() {
        Topic topic = new Topic();
        topic.name = "Initial Topic";
        topic.parentId = null;
        topicRepository.insert(topic);
        idToUpdate = topic.id;

        Topic parent = new Topic();
        parent.name = "Parent Topic";
        parent.parentId = null;
        topicRepository.insert(parent);
        parentId = parent.id;

        Topic topicWithParent = new Topic();
        topicWithParent.name = "New Topic With Parent";
        topicWithParent.parentId = parentId.toString();
        topicRepository.insert(topicWithParent);
        idWithParent = topicWithParent.id;
    }

    @Test
    @DisplayName("Update Topic: Basic")
    public void testNameTopicUpdate() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idToUpdate.toString());
        jsonAsMap.put("topicName", "Updated Topic");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/update")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("parentId", nullValue());
    }

    @Test
    @DisplayName("Update Topic: Add parent")
    public void testAddParentTopicUpdate() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idToUpdate.toString());
        jsonAsMap.put("topicName", "Updated Topic");
        jsonAsMap.put("parentId", parentId.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/update")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("parentId", notNullValue());
    }

    @Test
    @DisplayName("Update Topic: Remove parent")
    public void testRemoveParentTopicUpdate() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idWithParent.toString());
        jsonAsMap.put("topicName", "Updated Topic");
        jsonAsMap.put("parentId", null);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/update")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("parentId", nullValue());
    }

    @Test
    @DisplayName("Update Topic: Non-Existent Topic")
    public void testUpdateNonExistentTopic() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "nonExistentId");
        jsonAsMap.put("topicName", "Updated Topic");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/update")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Update Topic: Non-Existent Parent Topic")
    public void testUpdateWithNonExistentParentTopic() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idToUpdate.toString());
        jsonAsMap.put("topicName", "Updated Topic");
        jsonAsMap.put("parentId", "nonExistentParentId");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/update")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Update Topic: Invalid Topic Name")
    public void testUpdateWithInvalidTopicName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idToUpdate.toString());
        jsonAsMap.put("topicName", "Invalid Topic @123");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/update")
                .then()
                .statusCode(400);
    }
}
