package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class UpdateActivityControllerTest {

    private ObjectId idToUpdate;
    private ObjectId idWithParent;

    @Inject
    ActivityRepository courseRepository;

    @BeforeEach
    void setUp() {
        ExtraActivity course = new ExtraActivity();
        course.name = "Initial Course";
        courseRepository.insert(course);
        idToUpdate = course.id;

    }

    @Test
    @DisplayName("Update Activity: Non-Existent Activity")
    public void testUpdateNonExistentCourse() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "nonExistentId");
        jsonAsMap.put("name", "Updated Activity");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/extra/update")
                .then()
                .statusCode(400);
    }



    @Test
    @DisplayName("Update Activity: Invalid Activity Name")
    public void testUpdateWithInvalidCourseName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idToUpdate.toString());
        jsonAsMap.put("name", "Invalid Activity @123");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/extra/update")
                .then()
                .statusCode(400);
    }
}