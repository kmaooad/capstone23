package edu.kmaooad.capstone23.tags.controllers;

import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;
import jakarta.inject.Inject;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@QuarkusTest
public class DeleteTagControllerTests {

    @Inject
    TagRepository tagRepository;

    @Test
    @DisplayName("Delete Tag: Basic")
    public void testDeleteTag() {
        String tagId = createPersistedTag("Tag to Delete");
        deleteTagAndAssert(tagId, 200, notNullValue());
    }

    @Test
    @DisplayName("Delete Tag: Nonexistent")
    public void testDeleteNonexistentTag() {
        String nonExistentTagId = "123456789101112131415161";
        deleteTagAndAssert(nonExistentTagId, 400, nullValue());
    }

    private String createPersistedTag(String tagName) {
        Tag tag = new Tag();
        tag.tagName = tagName;
        tagRepository.persist(tag);
        return tag.id.toString();
    }

    private void deleteTagAndAssert(String tagId, int expectedStatusCode, Matcher<?> bodyMatcher) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", tagId);

        ValidatableResponse response = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/tags/delete")
                .then()
                .statusCode(expectedStatusCode);

        if (bodyMatcher != null) {
            response.body("id", bodyMatcher);
        }
    }

    @AfterEach
    void teardown() {
        tagRepository.deleteAll();
    }
}

