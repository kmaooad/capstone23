package edu.kmaooad.capstone23.experts.controllers;

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
public class DeleteExpertControllerTest {
    private static final String ORG_NAME = "Random Org";
    @Inject
    OrgsRepository orgsRepository;

    @BeforeEach
    public void setUp() {
        createTestOrg();
        createTestExpert();
    }

    @Test
    @DisplayName("Delete Expert: Basic")
    public void testBasicExpertDeletion() {
        ObjectId expertToDelete = createTestExpert();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", expertToDelete.toHexString());

        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/delete")
                .then()
                .statusCode(200);

        // ERROR: the expert is already deleted
        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/delete")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Delete Expert: invalid input")
    public void testInvalidInputExpertDeletion() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "avtor_idei_mc_petia");

        // ERROR: incorrect ID
        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/delete")
                .then()
                .statusCode(400);
    }

    @AfterEach
    public void tearDown() {
        orgsRepository.deleteAll();
    }

    private ObjectId createTestOrg() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", ORG_NAME);
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

        return new ObjectId(objectId);
    }

    private ObjectId createTestExpert() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertName", "John Doe");
        jsonAsMap.put("orgName", ORG_NAME);

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
}
