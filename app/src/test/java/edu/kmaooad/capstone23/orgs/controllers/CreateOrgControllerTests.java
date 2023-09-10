package edu.kmaooad.capstone23.orgs.controllers;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class CreateOrgControllerTests {

    @Test
    @DisplayName("Create Org: Basic")
    public void testBasicOrgCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "NaUKMA");
        jsonAsMap.put("industry", "Education");
        jsonAsMap.put("website", "https://www.ukma.edu.ua/eng/");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Org: Name validation")
    public void testOrgCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "Kma0oad_2023");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/create")
                .then()
                .statusCode(400);
    }
}