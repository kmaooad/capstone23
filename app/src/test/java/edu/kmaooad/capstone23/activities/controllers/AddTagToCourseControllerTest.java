package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AddTagToCourseControllerTest {

    @Inject
    TagRepository tagRepository;
    @Inject
    CourseRepository courseRepository;
    @BeforeEach
    public void setup() {
        var course = new Course();
        course.name = "TestCourse";
        course.tags = new ArrayList<>();
        courseRepository.insert(course);

        var tag = new Tag();
        tag.tagName = "TestTag";
        tagRepository.persist(tag);
    }

    @Test
    @DisplayName("Add Tag to Course")
    public void testAddTagToCourse() {
        var tagName = "TestTag";
        var courseName = "TestCourse";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("tagName", tagName);
        jsonAsMap.put("courseName", courseName);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/add/tag")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Add Tag to Course Failure")
    public void testAddTagToCourseFailure() {
        var tagName = "TestTag";
        var courseName = "TestCourse2";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("tagName", tagName);
        jsonAsMap.put("courseName", courseName);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/add/tag")
                .then()
                .statusCode(400);
    }

    @AfterEach
    public void teardown() {
        courseRepository.deleteAll();
        tagRepository.deleteAll();
    }

}
