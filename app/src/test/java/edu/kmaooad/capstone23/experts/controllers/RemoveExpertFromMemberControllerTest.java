package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RemoveExpertFromMemberControllerTest {
    private ObjectId orgId;
    private ObjectId memberId;
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    MembersRepository membersRepository;

    @BeforeEach
    public void setUp() {
        orgId = createTestOrg();
    }

    @Test
    @DisplayName("Remove Expert From Member: Basic")
    public void testBasicExpertFromMemberRemoval() {
        memberId = createExpertMember();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("memberId", memberId.toHexString());
        jsonAsMap.put("orgName", orgsRepository.findById(orgId).name);

        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/expert/delete")
                .then()
                .statusCode(200);

        // ERROR: the expert status is already removed
        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/expert/delete")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Remove Expert From Member Handler: the member is already not an expert")
    public void testInvalidExpertFromMemberRemoval() {
        memberId = createBasicMember();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("memberId", memberId.toHexString());
        jsonAsMap.put("orgName", orgsRepository.findById(orgId).name);

        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/expert/delete")
                .then()
                .statusCode(400);
    }

    @AfterEach
    public void tearDown() {
        membersRepository.deleteAll();
        orgsRepository.deleteAll();
    }

    private ObjectId createTestOrg() {
        Org org = new Org();
        org.name = "Brovary Club";
        org.industry = "Some random industry";
        org.website = "Some random website";
        orgsRepository.insert(org);
        return org.id;
    }

    private ObjectId createExpertMember() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "Petro");
        jsonAsMap.put("lastName", "Mostavchuk");
        jsonAsMap.put("orgId", orgId.toString());
        jsonAsMap.put("email", "mcpetia@ukr.net");
        jsonAsMap.put("isExpert", "true");

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

    private ObjectId createBasicMember() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "Bob");
        jsonAsMap.put("lastName", "Kvasolia");
        jsonAsMap.put("orgId", orgId.toString());
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
