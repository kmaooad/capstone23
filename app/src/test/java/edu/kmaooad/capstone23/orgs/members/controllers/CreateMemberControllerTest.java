package edu.kmaooad.capstone23.orgs.members.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateMemberControllerTest {
    @Test
    @DisplayName("Create Member: Basic")
    public void testBasicOrgCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", "64f9db2b6cf30055cb6ddcbe");
        jsonAsMap.put("email", "email@email.com");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Member: Email validation")
    public void testOrgCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", "64f9db2b6cf30055cb6ddcbe");
        jsonAsMap.put("email", "email.com");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create")
                .then()
                .statusCode(400);
    }
}
