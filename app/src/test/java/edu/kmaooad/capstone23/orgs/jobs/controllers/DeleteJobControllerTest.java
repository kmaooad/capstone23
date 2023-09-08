package edu.kmaooad.capstone23.orgs.jobs.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteJobControllerTest {

    @Test
    @DisplayName("Delete job: valid input")
    public void testBasicJobDeleting() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("_id", "64faf6ad341e202c91f76c84");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .delete("/jobs/delete")
                .then()
                .statusCode(200);
    }
}
