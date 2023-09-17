package edu.kmaooad.capstone23.experts.controllers;

import io.quarkus.test.junit.QuarkusTest;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AssignExpertToMemberControllerTest {

    @Test
    @DisplayName("Assign Expert to member: Basic")
    public void testAssignExpertToMember() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("memberId", createTestMember().toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/assign_expert_to_member")
                .then()
                .statusCode(200);
    }

    private ObjectId createTestOrg() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "Persyk Inc");

        String objectId = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/create")
                .then()
                .statusCode(200)
                .extract()
                .path("orgId");

        return new ObjectId(objectId);
    }

    private ObjectId createTestMember() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "Bob");
        jsonAsMap.put("lastName", "Kvasolia");
        jsonAsMap.put("orgId", createTestOrg().toString());
        jsonAsMap.put("email", "bob@ukr.net");
        jsonAsMap.put("isExpert", "false");

        String objectId = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create")
                .then()
                .statusCode(200)
                .extract()
                .path("memberId");

        return new ObjectId(objectId);
    }
}
