package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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

    @ParameterizedTest
    @DisplayName("Update Activity: Non-Existent Activity")
    public void testUpdateNonExistentActivity(Map<String, Object> jsonAsMap) {
        given().contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/extra/create")
                .then()
                .statusCode(400);
    }




    @Test
    @DisplayName("Update Activity:valid Name")
    public void testUpdateWithInvalidCourseName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idToUpdate.toString());
        jsonAsMap.put("name", "Activity name");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/extra/update")
                .then()
                .statusCode(200);
    }
}