package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AssignExpertToMemberControllerTest {

    @Inject
    MembersRepository membersRepository;
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    ExpertsRepository expertsRepository;

    @Test
    @DisplayName("Assign Expert to member: Basic")
    public void testAssignExpertToMember() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        Member member = createTestMember();
        jsonAsMap.put("memberId", member.id);

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

    private Member createTestMember() {
        Member member = new Member();
        member.firstName = "First";
        member.lastName = "Last";
        member.isExpert = false;
        member.isAdmin = false;
        member.email = "mail@test.com";
        member.orgId = createTestOrg();

        membersRepository.insert(member);

        return member;
    }
}
