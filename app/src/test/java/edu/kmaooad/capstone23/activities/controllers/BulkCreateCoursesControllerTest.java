package edu.kmaooad.capstone23.activities.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BulkCreateCoursesControllerTest {
    @Test
    @DisplayName("Bulk Create Course successful")
    public void testCourseCreation() {
        List<Map<String, Object>> coursesList = new ArrayList<>();
        Map<String, Object> firstCourse = new HashMap<>();
        Map<String, Object> secondCourse = new HashMap<>();
        firstCourse.put("name", "Linear algebra");
        secondCourse.put("name", "History");
        coursesList.add(firstCourse);
        coursesList.add(secondCourse);
        Map<String, Object> body = new HashMap<>();
        body.put("coursesList", coursesList);

        given()
            .contentType("application/json")
            .body(body)
            .when()
            .post("/activities/courses/bulk/create")
            .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("Bulk Create Course name validation")
    public void testIncorrectNameCourseCreate() {
        List<Map<String, Object>> coursesList = new ArrayList<>();
        Map<String, Object> firstCourse = new HashMap<>();
        Map<String, Object> secondCourse = new HashMap<>();
        firstCourse.put("name", "Linear algebra");
        secondCourse.put("name", "");
        coursesList.add(firstCourse);
        coursesList.add(secondCourse);
        Map<String, Object> body = new HashMap<>();
        body.put("coursesList", coursesList);

        given()
            .contentType("application/json")
            .body(body)
            .when()
            .post("/activities/courses/bulk/create")
            .then()
            .statusCode(400);
    }

    @Test
    @DisplayName("Bulk Create Course non-empty list validation")
    public void testEmptyCourseCreate() {
        List<Map<String, Object>> coursesList = new ArrayList<>();
        Map<String, Object> body = new HashMap<>();
        body.put("coursesList", coursesList);

        given()
            .contentType("application/json")
            .body(body)
            .when()
            .post("/activities/courses/bulk/create")
            .then()
            .statusCode(400);
    }
}
