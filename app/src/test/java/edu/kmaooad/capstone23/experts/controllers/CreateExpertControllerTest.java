package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateExpertControllerTest {
    private static final String ORG_NAME = "Random Org";
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    ExpertsRepository expertsRepository;

    @BeforeEach
    public void setUp() {
        createTestOrg();
    }

    @Test
    @DisplayName("Create Expert: Basic")
    public void testBasicExpertCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertName", "ArkhypchukStepanenko");
        jsonAsMap.put("orgName", ORG_NAME);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Expert: Name validation")
    public void testExpertCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertName", "ArkhypchukStepanenko ∂ƒ©");
        jsonAsMap.put("orgName", ORG_NAME);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/create")
                .then()
                .statusCode(400);
    }

    @AfterEach
    public void tearDown() {
        expertsRepository.deleteAll();
        orgsRepository.deleteAll();
    }

    private void createTestOrg() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", ORG_NAME);
        jsonAsMap.put("website", "foo");
        jsonAsMap.put("industry", "foo");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/create")
                .then()
                .statusCode(200);
    }
}
