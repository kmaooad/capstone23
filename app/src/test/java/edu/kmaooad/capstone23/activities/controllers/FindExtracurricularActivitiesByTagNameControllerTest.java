package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class FindExtracurricularActivitiesByTagNameControllerTest {
    @Inject
    TagRepository tagRepository;
    @Inject
    ExtracurricularActivityRepository extracurricularActivityRepository;


    @BeforeEach
    public void init() {
        extracurricularActivityRepository.deleteAll();
        tagRepository.deleteAll();

        tagRepository.persist(new Tag("tag1"));
        tagRepository.persist(new Tag("tag2"));
        tagRepository.persist(new Tag("tag3"));
    }

    @AfterEach
    public void teardown() {
        extracurricularActivityRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    public void findOnlyExtracurricularActivitiesWithGivenTag() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();
        extracurricularActivity.extracurricularActivityName = "Activity 1";
        extracurricularActivity.tags = new ArrayList<>();
        extracurricularActivity.tags.add(new Tag("tag1"));
        extracurricularActivityRepository.persist(extracurricularActivity);
        ExtracurricularActivity extracurricularActivity1 = new ExtracurricularActivity();
        extracurricularActivity1.tags = new ArrayList<>();
        extracurricularActivity1.extracurricularActivityName = "Activity 2";
        extracurricularActivityRepository.persist(extracurricularActivity1);


        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("tagName", "tag1");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/extracurricularActivity/find/tag/")
                .then()
                .statusCode(200)
                .body("extracurricularActivities", hasSize(1));
    }

    @Test
    public void findsByTagIfExtracurricularActivityHasMultipleTags() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();
        extracurricularActivity.extracurricularActivityName = "Activity 1";
        extracurricularActivity.tags = new ArrayList<>();
        extracurricularActivity.tags.add(new Tag("tag1"));
        extracurricularActivity.tags.add(new Tag("tag2"));
        extracurricularActivityRepository.persist(extracurricularActivity);
        ExtracurricularActivity extracurricularActivity1 = new ExtracurricularActivity();
        extracurricularActivity1.tags = new ArrayList<>();
        extracurricularActivity1.extracurricularActivityName = "Activity 2";
        extracurricularActivity1.tags.add(new Tag("tag2"));
        extracurricularActivityRepository.persist(extracurricularActivity1);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("tagName", "tag1");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/extracurricularActivity/find/tag/")
                .then()
                .statusCode(200)
                .body("extracurricularActivities", hasSize(1));
    }

    @Test
    public void notFoundIfTagNameDoesNotExist() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("tagName", "null");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/extracurricularActivity/find/tag/")
                .then()
                .statusCode(400);
    }
}
