package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteExtracurricularActivityControllerTest {
    private String activityId;

    @Inject
    ExtracurricularActivityRepository extracurricularActivityRepository;

    @BeforeEach
    void setUp() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();
        extracurricularActivity.extracurricularActivityName = "Football to Delete";
        extracurricularActivity.extracurricularActivityDate = new Date();
        extracurricularActivityRepository.insert(extracurricularActivity);

        activityId = extracurricularActivity.id.toString();
    }

    @Test
    @DisplayName("Delete Extracurricular Activity: Basic")
    public void testBasicActivityDeletion() {
        given()
                .contentType("application/json")
                .body("{\"id\":\"" + activityId + "\"}")
                .when()
                .post("/extracurricularActivity/delete")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete Extracurricular Activity: No such department")
    public void testActivityDeletionWithNonExistentId() {
        String nonexistentId = "6434534t234r23rsdfsdf4t35ta3";

        given()
                .contentType("application/json")
                .body("{\"id\":\"" + nonexistentId + "\"}")
                .when()
                .post("/extracurricularActivity/delete")
                .then()
                .statusCode(400);
    }
}
