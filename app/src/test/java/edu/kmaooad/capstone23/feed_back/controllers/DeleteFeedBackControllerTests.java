package edu.kmaooad.capstone23.feed_back.controllers;

import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBackRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteFeedBackControllerTests {
    @Inject
    private FeedBackRepository repository;

    private ObjectId idToDelete;

    @BeforeEach
    void setUp() {
        FeedBack feedBack = new FeedBack();
        feedBack.topic = "Decorators";
        feedBack.text = "Some text";
        feedBack.date = new Date();
        repository.insert(feedBack);
        idToDelete = feedBack.id;
    }


    @Test
    @DisplayName("Delete Feedback: Basic")
    public void testBasicFeedBackDeletion() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idToDelete.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/feedback/delete")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete Feedback: Non-existent")
    public void testNonExistentFeedBackDeletion() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "ab12412");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/feedback/delete")
                .then()
                .statusCode(400);
    }

}
