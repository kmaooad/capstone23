package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.orgs.drivers.OrgDriver;
import io.quarkus.test.junit.QuarkusTest;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class DeleteOrgControllerTests {
    @Inject
    OrgDriver orgDriver;

    private ObjectId createdOrgId;
    
    @BeforeEach
    void setUp() {
        var org = orgDriver.createOrg();
        createdOrgId = org.id;
    }
    

    @Test
    public void testDeleteOrganization() {
        given()
                .contentType("application/json")
                .body("{\"orgId\": \"" + createdOrgId.toString() + "\"}")
                .when()
                .post("/orgs/delete")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeleteNotExistingOrg() {
        given()
                .contentType("application/json")
                .body("{\"orgId\": \"randomString\" }")
                .when()
                .post("/orgs/delete")
                .then()
                .statusCode(400);
    }
}
