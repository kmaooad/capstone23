package edu.kmaooad.capstone23.tags.controllers;

import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class DeleteTagControllerTests {

    @Inject
    TagRepository tagRepository;

    @Test
    @DisplayName("Delete Tag: Basic")
    public void testDeleteTag() {
        Tag tag = new Tag();
        tag.tagName = "Tag to Delete";
        tagRepository.persist(tag);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", tag.id.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/tags/delete")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Delete Tag: Nonexistent")
    public void testDeleteNonexistentTag() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "123456789101112131415161");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/tags/delete")
                .then()
                .statusCode(400);
    }

    @AfterEach
    void teardown() {
        tagRepository.deleteAll();
    }
}
