package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
@QuarkusTest
public class DeleteActivityControllerTest {



        private ObjectId idToDelete;


        @Inject
        ActivityRepository courseRepository;

        @BeforeEach
        void setUp() {
            ExtraActivity course = new ExtraActivity();
            course.name = "Initial Course";
            courseRepository.insert(course);
            idToDelete = course.id;

        }

        @Test
        @DisplayName("Update Activity: Non-Existent Activity")
        public void testUpdateNonExistentActivity() {
            Map<String, Object> jsonAsMap = new HashMap<>();
            jsonAsMap.put("id", "nonExistentId");

            given()
                    .contentType("application/json")
                    .body(jsonAsMap)
                    .when()
                    .post("/activities/extra/delete")
                    .then()
                    .statusCode(400);
        }


        @Test
        @DisplayName("Update Activity:valid Name")
        public void testUpdateWithInvalidCourseName() {
            Map<String, Object> jsonAsMap = new HashMap<>();
            jsonAsMap.put("id", idToDelete.toString());


            given()
                    .contentType("application/json")
                    .body(jsonAsMap)
                    .when()
                    .post("/activities/extra/delete")
                    .then()
                    .statusCode(200);
        }
    }