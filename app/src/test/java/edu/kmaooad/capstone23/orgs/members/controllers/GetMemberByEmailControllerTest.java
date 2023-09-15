package edu.kmaooad.capstone23.orgs.members.controllers;

import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.members.TestWithMembersSetUp;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class GetMemberByEmailControllerTest extends TestWithMembersSetUp {

    private final String email = "newEmail@gmail.com";

    @BeforeEach
    void setUp() {
        var org = new Org();
        org.name = "Ubisoft";
        orgsRepository.insert(org);

        firstOrgMembers = new ArrayList<>();
        Member member = new Member();
        member.firstName = "Another";
        member.lastName = "Member";
        member.orgId = org.id;
        member.email = email;
        membersRepository.insert(member);
    }

    @Test
    @DisplayName("Read member: Basic")
    public void testMemberByEmailRead() {
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

    @Test
    @DisplayName("Read member: Wrong email")
    public void testMemberByWrongEmailRead() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", email + "uniqueWrongEmailPostfix");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/get/email")
                .then()
                .statusCode(400);
    }
}