package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
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

    @BeforeEach
    void setUp() {
        var org = new Org();
        org.name = "NaUKMA";
        org.industry = "Education";
        org.website = "https://www.ukma.edu.ua/eng/";
        repo.insert(org);
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
    public void testOrgUpdateOnNotExisting() {
        Map<String, Object> body = new HashMap<>();

        body.put("orgId", "XD");
        body.put("orgName", "KPI");
        body.put("industry", "test");
        body.put("website", "https://www.google.com");

        given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/departments/update")
                .then()
                .statusCode(400);
    }
}
