package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BulkUpdateCoursesControllerTest {
    private ObjectId idToUpdate;
    private ObjectId secondIdToUpdate;

    @Inject
    CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        Course course = new Course();
        course.name = "Initial Course";
        courseRepository.insert(course);
        idToUpdate = course.id;
        Course secondCourse = new Course();
        secondCourse.name = "Second Course";
        courseRepository.insert(secondCourse);
        secondIdToUpdate = secondCourse.id;
    }
    @Test
    @DisplayName("Bulk Update Course successful")
    public void testCourseUpdate() {
        List<Map<String, Object>> coursesList = new ArrayList<>();
        Map<String, Object> firstCourse = new HashMap<>();
        Map<String, Object> secondCourse = new HashMap<>();
        firstCourse.put("name", "Linear algebra");
        firstCourse.put("id", idToUpdate.toHexString());
        secondCourse.put("name", "History");
        secondCourse.put("id", secondIdToUpdate.toHexString());
        coursesList.add(firstCourse);
        coursesList.add(secondCourse);
        Map<String, Object> body = new HashMap<>();
        body.put("coursesList", coursesList);

        given()
            .contentType("application/json")
            .body(body)
            .when()
            .post("/activities/courses/bulk/update")
            .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("Bulk Update Course unsuccessful if ids are incorrect")
    public void testCoursUpdateWithIncorrectIds() {
        List<Map<String, Object>> coursesList = new ArrayList<>();
        Map<String, Object> firstCourse = new HashMap<>();
        Map<String, Object> secondCourse = new HashMap<>();
        firstCourse.put("name", "Linear algebra");
        firstCourse.put("id", idToUpdate.toHexString());
        secondCourse.put("name", "History");
        secondCourse.put("id", idToUpdate.toHexString());
        coursesList.add(firstCourse);
        coursesList.add(secondCourse);
        Map<String, Object> body = new HashMap<>();
        body.put("coursesList", coursesList);

        given()
            .contentType("application/json")
            .body(body)
            .when()
            .post("/activities/courses/bulk/update")
            .then()
            .statusCode(400);
    }
}
