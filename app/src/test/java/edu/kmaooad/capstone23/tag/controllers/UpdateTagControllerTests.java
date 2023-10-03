package edu.kmaooad.capstone23.tag.controllers;

import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class UpdateTagControllerTests {
    @Inject
    TagRepository tagRepository;

    @Test
    @DisplayName("Update Tag : Name Validation")
    public void testUpdateTagForInvalidGroupName() {
        var tag = new Tag();
        tag.tagName = "testTag";
        tagRepository.persist(tag);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",tag.id);
        jsonAsMap.put("tagName", "");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/tags/update")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Update Tag : Invalid ID")
    public void testUpdateTagForInvalidID() {
        var tag = new Tag();
        tag.tagName = "testTag";
        tagRepository.persist(tag);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "abcdef");
        jsonAsMap.put("tagName", "new tag name");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/tags/update")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("Update Tag : Non Existent Tag")
    public void testUpdateNonExistentTag() {
        var tag = new Tag();
        tag.tagName = "testTag";
        tagRepository.persist(tag);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", ObjectId.get());
        jsonAsMap.put("tagName", "");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/tags/update")
                .then()
                .statusCode(400);
    }

    @AfterEach
    public void teardown() {
        tagRepository.deleteAll();
    }
}
