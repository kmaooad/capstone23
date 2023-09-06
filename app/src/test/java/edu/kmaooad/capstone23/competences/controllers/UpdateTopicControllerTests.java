package edu.kmaooad.capstone23.competences.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateTopicControllerTests {

    @Test
    @DisplayName("Update Topic: Basic")
    public void testBasicOrgCreation() {
        var topicId = createTopic();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", topicId);
        jsonAsMap.put("topicName", "drinks");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/topics/update")
                .then()
                .statusCode(200);
    }

    private String createTopic() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("topicName", "food");

        return given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/topics/create")
                .then()
                .statusCode(200)
                .extract()
                .path("topic");
    }
}
