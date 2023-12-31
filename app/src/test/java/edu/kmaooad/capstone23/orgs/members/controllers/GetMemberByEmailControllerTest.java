package edu.kmaooad.capstone23.orgs.members.controllers;

import edu.kmaooad.capstone23.common.Mocks;
import edu.kmaooad.capstone23.orgs.members.TestWithMembersSetUp;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class GetMemberByEmailControllerTest extends TestWithMembersSetUp {

    private final String email = Mocks.mockValidEmail();

    @Test
    @DisplayName("Read member: Basic")
    public void testMemberByEmailRead() {
        createOrgWithMember(email);
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", email);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/get/email")
                .then()
                .statusCode(200);
    }
}