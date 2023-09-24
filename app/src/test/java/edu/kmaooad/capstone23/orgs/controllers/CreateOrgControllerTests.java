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
    @DisplayName("Create Org: With email domain")
    public void testBasicOrgWithEmailDomainCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "NaUKMA");
        jsonAsMap.put("industry", "Education");
        jsonAsMap.put("website", "https://www.ukma.edu.ua/eng/");
        jsonAsMap.put("emailDomain", "gmail.com");

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
        jsonAsMap.put("website", "foo");
        jsonAsMap.put("industry", "foo");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/create")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create Org: Website validation")
    public void testOrgCreationWithWebsiteValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "Kma2023");
        jsonAsMap.put("website", "");
        jsonAsMap.put("industry", "foo");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/create")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create Org: Industry validation")
    public void testOrgCreationWithIndustryValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "Kma2023");
        jsonAsMap.put("website", "foo");
        jsonAsMap.put("industry", "");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/create")
                .then()
                .statusCode(400);
    }
}