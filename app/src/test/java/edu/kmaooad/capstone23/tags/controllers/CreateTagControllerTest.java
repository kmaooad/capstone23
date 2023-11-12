package edu.kmaooad.capstone23.tags.controllers;

import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateTagControllerTest {

    @Inject
    TagRepository tagRepository;

    @Test
    @DisplayName("Create Tag")
    public void testTagCreation() {
        var tagName = "OOAD";
        createAndPostTag(tagName, 200);
    }

    @Test
    @DisplayName("Test invalid tagName")
    public void testInvalidTagName() {
        var tagName = "O";
        createAndPostTag(tagName, 400);
    }

    private void createAndPostTag(String tagName, int expectedStatusCode) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("tagName", tagName);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/tags/create")
                .then()
                .statusCode(expectedStatusCode);
    }

    @AfterEach
    public void teardown() {
        tagRepository.deleteAll();
    }
}
