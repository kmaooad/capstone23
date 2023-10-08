package edu.kmaooad.capstone23.feed_back.controllers;

import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBackRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class UpdateFeedBackControllerTests {

    @Inject
    FeedBackRepository repository;
    private FeedBack feedBack;

    @BeforeEach
    void setUp() {
        feedBack = new FeedBack();
        feedBack.topic = "Review Result";
        feedBack.text = "Test of review result.";
        feedBack.date = new Date();
        repository.insert(feedBack);
    }

    @AfterEach
    void after() {
        repository.delete(feedBack);
    }


    @Test
    @DisplayName("Update FeedBack: Basic")
    public void testBasicFeedBackUpdate() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("feedBackId", feedBack.id.toString());
        jsonAsMap.put("topic", "Review Result");
        jsonAsMap.put("text", "Test of review result.");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/feedback/update")
                .then()
                .statusCode(200)
                .body("feedBackId", equalTo(feedBack.id.toString()))
                .body("feedBackText", equalTo("Test of review result."));
    }

    @Test
    @DisplayName("Update FeedBack: Name validation")
    public void testFeedBackUpdateWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("feedBackId", feedBack.id.toString());
        jsonAsMap.put("text", "Test of review result.");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/feedback/update")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Update FeedBack: with Non-existed id")
    public void testFeedBackUpdateWithNonExistedId() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("feedBackId", new ObjectId().toString());
        jsonAsMap.put("topic", "Review Result");
        jsonAsMap.put("text", "Test of review result.");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/feedback/update")
                .then()
                .statusCode(400);
    }


    @Test
    @DisplayName("Update FeedBack: Topic validation too many symbols")
    public void testUpdateFeedBackTopicValidationTooManySymbols() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("feedBackId", feedBack.id.toString());
        jsonAsMap.put("topic", "Too many characters for topic");
        jsonAsMap.put("text", "Test of review result.");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/feedback/update")
                .then()
                .statusCode(400);
    }

}
