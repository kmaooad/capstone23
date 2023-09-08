package edu.kmaooad.capstone23.orgs.jobs.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeleteJobControllerTest {

    @Test
    @DisplayName("Delete job: valid input")
    public void testBasicJobDeleting() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Professor");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .delete("/jobs/delete")
                .then()
                .statusCode(200);
    }

}
