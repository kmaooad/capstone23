package edu.kmaooad.capstone23.feed_back.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;


@QuarkusTest
public class CreateFeedBackControllerTests {

    @Test
    @DisplayName("Create FeedBack: Basic")
    public void testBasicFeedBackCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("topic", "Review Result");
        jsonAsMap.put("text", "Test of review result.");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/feedBack/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create FeedBack: Topic validation")
    public void testFeedBackCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("topic", "Wrong");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/feedBack/create")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create FeedBack: Topic validation less symbols")
    public void testCreateFeedBackTopicValidationLessSymbols() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("topic", "less");
        jsonAsMap.put("text", "Create FeedBack Topic Validation");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/feedBack/create")
                .then()
                .statusCode(400);
    }
}