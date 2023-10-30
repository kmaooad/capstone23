package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.drivers.OrgDriver;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RequestToJoinOrgControllerTest {

    private String idToUpdate;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    OrgDriver orgDriver;

    @BeforeEach
    void setUp() {
        Org org = orgDriver.createOrg();

        idToUpdate = org.id.toString();
    }


    @Test
    @DisplayName("Create Request to Join Org: Basic")
    public void testBasicRequestToJoinOrg() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String userName = "user1";
        String departmentId = idToUpdate;

        jsonAsMap.put("userName", userName);
        jsonAsMap.put("orgId", departmentId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/request")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("Create Request to Join Org: No such organization")
    public void testRequestToJoinOrgWithNonExistentId() {
        String nonexistentId = "64fbb243275c1111167b87a3";
        String userName = "user1";

        given()
                .contentType("application/json")
                .body("{\"userName\":\"" + userName + "\",\"orgId\":\"" + nonexistentId + "\"}")
                .when()
                .post("/orgs/request")
                .then()
                .statusCode(400);
    }


}
