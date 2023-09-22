package edu.kmaooad.capstone23.competences.topics.controllers;

import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class DeleteTopicControllerTests {
    @BeforeAll
    static void deleteAllData() {
        TopicRepository repository = new TopicRepository();
        repository.deleteAll();
    }

    private ObjectId idToDelete;
    private ObjectId parentId;
    private ObjectId idWithParent;

    @Inject
    TopicRepository topicRepository;

    @BeforeEach
    void setUp() {
        Topic topic = new Topic();
        topic.name = "Topic w/o parent";
        topic.parentId = null;
        topicRepository.insert(topic);
        idToDelete = topic.id;

        Topic parent = new Topic();
        parent.name = "Parent Topic";
        parent.parentId = null;
        topicRepository.insert(parent);
        parentId = parent.id;

        Topic topicWithParent = new Topic();
        topicWithParent.name = "Topic With Parent";
        topicWithParent.parentId = parentId.toString();
        topicRepository.insert(topicWithParent);
        idWithParent = topicWithParent.id;
    }

    @Test
    @DisplayName("Delete Topic: Basic")
    public void testDeleteTopic() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idToDelete.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/delete")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Delete Topic: With Children")
    public void testDeleteTopicWithChildren() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", parentId.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/delete")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Delete Topic: Child")
    public void testDeleteTopicChild() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idWithParent.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/competences/topic/delete")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }
}
