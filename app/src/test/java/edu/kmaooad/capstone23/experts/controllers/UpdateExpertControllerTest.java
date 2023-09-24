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
        jsonAsMap.put("orgId", org.id.toString());
        jsonAsMap.put("orgName", org.name);

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
        jsonAsMap.put("orgName", org.name);

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
        jsonAsMap.put("website", "foo");
        jsonAsMap.put("industry", "foo");

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
