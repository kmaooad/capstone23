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
public class RejectOrgControllerTests {

    private static final String ORG_NAME = "Random Org";
    private static final String ORG_EMAIL = "Random Email";

    private String idToReject;

    @Inject
    private OrgsRepository orgsRepository;

    public String prepareTestBasicRejectOrg(Boolean isActive) {
        final Org org = new Org();
        org.name = ORG_NAME;
        org.isActive = isActive;
        orgsRepository.insert(org);
        return org.id.toString();
    }

    @Test
    @DisplayName("Reject Org: Basic")
    public void testRejectOrgBasic() {
        final String id = prepareTestBasicRejectOrg(true);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", id);
        jsonAsMap.put("email", ORG_EMAIL);
        jsonAsMap.put("reason", "We close 1234");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/reject")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Reject Org: Reason Validate")
    public void testRejectOrgReasonValidate() {
        final String id = prepareTestBasicRejectOrg(true);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", id);
        jsonAsMap.put("email", ORG_EMAIL);
        jsonAsMap.put("reason", "We");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/reject")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Reject Org: Already Rejected")
    public void testRejectOrgReasonAlreadyReject() {
        final String id = prepareTestBasicRejectOrg(false);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", id);
        jsonAsMap.put("email", ORG_EMAIL);
        jsonAsMap.put("reason", "We Test 123");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/reject")
                .then()
                .statusCode(400);
    }
}