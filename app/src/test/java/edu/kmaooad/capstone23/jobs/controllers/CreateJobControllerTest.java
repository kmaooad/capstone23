package edu.kmaooad.capstone23.jobs.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateJobControllerTest {

    private static final String JOB_CREATION_ENDPOINT = "/jobs/create";
    private static final String APPLICATION_JSON = "application/json";
    private static final int STATUS_OK = 200;
    private static final int STATUS_BAD_REQUEST = 400;

    private Map<String, Object> validPayload;
    private Map<String, Object> invalidPayload;

    @BeforeEach
    public void setup() {
        validPayload = createJobPayload("Professor");
        invalidPayload = createJobPayload("professor_of_linear_algebra");
    }

    private Map<String, Object> createJobPayload(String jobName) {
        return Map.of("name", jobName);
    }

    @Test
    @DisplayName("Create job: valid input")
    public void testBasicJobCreation() {
        given()
                .contentType(APPLICATION_JSON)
                .body(validPayload)
                .when()
                .post(JOB_CREATION_ENDPOINT)
                .then()
                .statusCode(STATUS_OK);
    }

    @Test
    @DisplayName("Create job: invalid input")
    public void testJobCreationWithNameValidation() {
        given()
                .contentType(APPLICATION_JSON)
                .body(invalidPayload)
                .when()
                .post(JOB_CREATION_ENDPOINT)
                .then()
                .statusCode(STATUS_BAD_REQUEST);
    }
}
