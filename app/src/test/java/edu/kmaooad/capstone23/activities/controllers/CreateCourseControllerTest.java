package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateCourseControllerTest {
    @Inject
    CourseRepository courseRepository;

    @BeforeEach
    void deleteAllData() {
        courseRepository.deleteAll();
    }

    @Test
    @DisplayName("Create Course successful")
    public void testCourseCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Linear algebra");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Course name validation")
    public void testEmptyCourseCreate() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/create")
                .then()
                .statusCode(400);
    }
}
