package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateExpertControllerTest {
    private Org org;
    @Inject
    OrgsRepository orgsRepository;

    @Test
    @DisplayName("Update Expert: Basic")
    public void testBasicExpertUpdate() {
        ObjectId expertId = createTestExpert();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", expertId.toHexString());
        jsonAsMap.put("expertName", "Jane Doe");
        jsonAsMap.put("org", org);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/update")
                .then()
                .statusCode(200);
    }

    private ObjectId createTestExpert() {
        createTestOrg();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertName", "Bob Smith");
        jsonAsMap.put("org", org);

        String objectId = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/create")
                .then()
                .statusCode(200)
                .extract()
                .path("expertId");

        return new ObjectId(objectId);
    }

    private void createTestOrg() {
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

        org = orgsRepository.findById(new ObjectId(objectId));
    }
}
