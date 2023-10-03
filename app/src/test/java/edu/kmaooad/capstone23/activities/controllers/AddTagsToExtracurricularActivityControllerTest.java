package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class AddTagsToExtracurricularActivityControllerTest {

    @Inject
    ExtracurricularActivityRepository activityRepository;

    @Inject
    TagRepository tagRepository;

    @AfterEach
    void tearDown() {
        activityRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    @DisplayName("Add Tags to Extracurricular Activity")
    public void testAddTagsToExtracurricularActivity() {
        ExtracurricularActivity activity = new ExtracurricularActivity();
        activity.extracurricularActivityName = "Activity 1";
        activityRepository.persist(activity);
        Tag tag1 = new Tag();
        tag1.tagName = "Tag 1";
        tagRepository.persist(tag1);
        Tag tag2 = new Tag();
        tag2.tagName = "Tag 2";
        tagRepository.persist(tag2);
        List<String> tagIds = Arrays.asList(tag1.id.toString(), tag2.id.toString());

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("extracurricularActivityName", activity.extracurricularActivityName);
        jsonAsMap.put("tagIds", tagIds);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/extracurricularActivity/add-tags")
                .then()
                .statusCode(200)
                .body("activityName", equalTo("Activity 1"))
                .body("tagNames", containsInAnyOrder("Tag 1", "Tag 2"));
    }
}

