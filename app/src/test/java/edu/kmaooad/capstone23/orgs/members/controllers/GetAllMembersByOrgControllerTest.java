package edu.kmaooad.capstone23.orgs.members.controllers;

import edu.kmaooad.capstone23.orgs.members.TestWithMembersSetUp;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class GetAllMembersByOrgControllerTest extends TestWithMembersSetUp {

    @Test
    @DisplayName("Read all members by orgId: Basic")
    void testAllMembersRead() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("ordId", firstOrg);
        jsonAsMap.put("page", 1);
        jsonAsMap.put("size", 1);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/get/all")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Read all members by orgId: Wrong page")
    void testAllMembersWithWrongPageRead() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("ordId", firstOrg);
        jsonAsMap.put("page", -1);
        jsonAsMap.put("size", 1);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/get/all")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Read all members by orgId: Wrong size")
    void testAllMembersWithWrongSizeRead() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("ordId", firstOrg);
        jsonAsMap.put("page", 1);
        jsonAsMap.put("size", -1);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/get/all")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Read all members by orgId: non-existent orgId")
    void testAllMembersWithWrongOrgIdRead() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("ordId", firstOrg + "9191841891");
        jsonAsMap.put("page", 1);
        jsonAsMap.put("size", 1);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/get/all")
                .then()
                .statusCode(200);
    }

}