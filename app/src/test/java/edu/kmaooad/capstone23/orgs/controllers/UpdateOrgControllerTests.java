package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.drivers.OrgDriver;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateOrgControllerTests {
    private ObjectId orgId;

    @Inject
    OrgsRepository repo;

    @Inject
    OrgDriver orgDriver;

    @BeforeEach
    void setUp() {
        Org org = orgDriver.createOrg();
        orgId = org.id;
    }

    @Test
    public void testOrgUpdate() {
        Map<String, Object> body = new HashMap<>();

        body.put("orgId", orgId.toString());
        body.put("orgName", "KPI");
        body.put("industry", "test");
        body.put("website", "https://www.google.com");


        given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/orgs/update")
                .then()
                .statusCode(200);
    }

    @Test
    public void testOrgUpdateWithEmailDomain() {
        Map<String, Object> body = new HashMap<>();

        body.put("orgId", orgId.toString());
        body.put("orgName", "KPI");
        body.put("industry", "test");
        body.put("website", "https://www.google.com");
        body.put("emailDomain", "gmail.com");


        given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/orgs/update")
                .then()
                .statusCode(200);
    }

    @Test
    public void testOrgUpdateOnNotExisting() {
        Map<String, Object> body = new HashMap<>();

        body.put("orgId", "65089e564656a99a99a0a552");
        body.put("orgName", "KPI");
        body.put("industry", "test");
        body.put("website", "https://www.google.com");

        given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/orgs/update")
                .then()
                .statusCode(400);
    }
}
