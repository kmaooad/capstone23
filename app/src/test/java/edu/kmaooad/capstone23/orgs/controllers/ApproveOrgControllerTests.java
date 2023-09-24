package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ApproveOrgControllerTests {

    private static final String testOrg_name = "NaUKMA";
    private static final String testOrg_email = "naukma@ukr.net";

    private String testOrg_id;

    @Inject
    private OrgsRepository orgsRepository;

    public String testData(Boolean isActive) {
        final Org org = new Org();
        org.name = testOrg_name;
        org.isActive = isActive;
        orgsRepository.insert(org);
        return org.id.toString();
    }

    @Test
    @DisplayName("Approve Org: Basic")
    public void testBasicOrgApprove() {
        final String id = testData(false);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgId", id);
        jsonAsMap.put("orgEmail", testOrg_email);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/approve")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Approve Org: Already approved")
    public void testRejectOrgReasonAlreadyReject() {
        final String id = testData(true);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", id);
        jsonAsMap.put("orgEmail", testOrg_email);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/approve")
                .then()
                .statusCode(400);
    }
}
