package edu.kmaooad.capstone23.orgs.members.controllers;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.members.ClearDbMemberTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateMemberControllerTest extends ClearDbMemberTest {
    @Inject
    OrgsRepository orgsRepository;
    private ObjectId createdOrgId;

    @BeforeEach
    void setUp() {
        var org = new Org();
        org.name = "NaUKMA";
        orgsRepository.insert(org);
        createdOrgId = org.id;
    }

    @Test
    @DisplayName("Create Member: Basic")
    public void testBasicMemberCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", createdOrgId.toString());
        jsonAsMap.put("email", "email@email.com1");

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
    public void testMemberCreationWithEmailValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", createdOrgId.toString());
        jsonAsMap.put("email", "email.com");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create Member: Org does not exist")
    public void testMemberCreationWithOrgExistenceValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        var newObjectId = new ObjectId();
        while (orgsRepository.findByIdOptional(newObjectId).isPresent())
            newObjectId = new ObjectId();
        jsonAsMap.put("orgId", newObjectId.toString());
        jsonAsMap.put("email", "email@a.com");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create")
                .then()
                .statusCode(400);
    }
}
