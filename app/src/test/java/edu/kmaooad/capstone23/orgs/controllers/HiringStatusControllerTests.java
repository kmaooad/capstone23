package edu.kmaooad.capstone23.orgs.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class HiringStatusControllerTests {

    @Test
    @DisplayName("Change hiring status: Basic")
    public void testHiringStatusChange() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgID", "64ff7fabc64e527ecae3f896");
        jsonAsMap.put("hiringStatus", "HIRING");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/change_hiring_status")
                .then()
                .statusCode(200);
    }

}