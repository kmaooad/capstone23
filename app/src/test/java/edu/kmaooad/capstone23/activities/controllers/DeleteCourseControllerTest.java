package edu.kmaooad.capstone23.activities.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@QuarkusTest
public class DeleteCourseControllerTest {

    @Test
    @DisplayName("Delete Course: Basic")
    public void testBasicCourseDeletion() {
        ObjectId idOfCreated = createCourse();
        assertNotNull(idOfCreated);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idOfCreated.toHexString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/delete")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete Course: Invalid ID")
    public void testCourseDeletionWithInvalidId() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        String invalidId = "abcde";
        jsonAsMap.put("id", invalidId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/delete")
                .then()
                .statusCode(400);
    }

    private ObjectId createCourse() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "WEB");

        String objectId =  given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/courses/create")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        return new ObjectId(objectId);
    }
}