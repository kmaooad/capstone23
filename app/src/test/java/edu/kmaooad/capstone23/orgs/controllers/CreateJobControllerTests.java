package edu.kmaooad.capstone23.orgs.controllers;


import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateJobControllerTests {


    @Test
    @DisplayName("Create Job: Basic")
    public void testBasicJobCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("jobName", "testJobName");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/create")
                .then()
                //.log().all() //for easier debugging
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Job: Name validation")
    public void testJobCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("jobName", "test_Invalid_Job_Name");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/create")
                .then()
                .statusCode(400);
    }
}
