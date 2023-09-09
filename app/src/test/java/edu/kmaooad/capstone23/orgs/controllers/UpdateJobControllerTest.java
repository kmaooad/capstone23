package edu.kmaooad.capstone23.orgs.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateJobControllerTest {

    //TODO: write update job test
    // logic: create job. change job, check if сonnection works. resolve getting 404 insteadof 200
//    @Test
//    @DisplayName("Update Job: Basic")
//    public void testBasicJobUpdate() {
//        Map<String, Object> jsonAsMap = new HashMap<>();
//        jsonAsMap.put("jobName", "testJobName");
//        jsonAsMap.replace("jobName", "anotherTestJObName");
//
//        given()
//                .contentType("application/json")
//                .body(jsonAsMap)
//                .when()
//                .patch("/jobd/update")
//                .then()
//                //.log().all() //for easier debugging
//                .statusCode(200);
//    }
}
