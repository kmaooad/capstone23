package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.commands.FindCoursesByTag;
import edu.kmaooad.capstone23.activities.controller.FindCoursesByTagNameController;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class FindCoursesByTagNameControllerTest {
    @Inject
    TagRepository tagRepository;
    @Inject
    CourseRepository courseRepository;

    @Inject
    FindCoursesByTagNameController findCoursesByTagNameController;

    @BeforeEach
    public void init() {
        courseRepository.deleteAll();
        tagRepository.deleteAll();

        tagRepository.persist(new Tag("tag1"));
        tagRepository.persist(new Tag("tag2"));
        tagRepository.persist(new Tag("tag3"));
    }

    @Test
    public void findOnlyCoursesWithGivenTag() {
        Course course = new Course();
        course.name = "course1";
        course.tags.add(new Tag("tag1"));
        courseRepository.persist(course);
        Course course2 = new Course();
        course2.name = "course2";
        courseRepository.persist(course2);


        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("tagName", "tag1");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/find/tag/")
                .then()
                .statusCode(200)
                .body("courses", hasSize(1));
    }

    @Test
    public void findsByTagIfCourseHasMultipleTags() {
        Course course = new Course();
        course.name = "course1";
        course.tags.add(new Tag("tag1"));
        course.tags.add(new Tag("tag2"));
        courseRepository.persist(course);
        Course course2 = new Course();
        course2.name = "course2";
        course2.tags.add(new Tag("tag2"));
        courseRepository.persist(course2);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("tagName", "tag1");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/find/tag/")
                .then()
                .statusCode(200)
                .body("courses", hasSize(1));
    }

    @Test
    public void notFoundIfTagNameDoesNotExist() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("tagName", "null");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/find/tag/")
                .then()
                .statusCode(400);
    }

    @AfterEach
    public void teardown() {
        courseRepository.deleteAll();
        tagRepository.deleteAll();
    }
}
