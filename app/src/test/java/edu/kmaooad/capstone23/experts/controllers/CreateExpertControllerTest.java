package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
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
    private static final String BASE_PATH = "/experts/create";
    private static final String CONTENT_TYPE_JSON = "application/json";

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
        createExpert("ArkhypchukStepanenko", ORG_NAME)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Expert: Name validation")
    public void testExpertCreationWithNameValidation() {
        createExpert("ArkhypchukStepanenko ∂ƒ©", ORG_NAME)
                .then()
                .statusCode(400);
    }

    @AfterEach
    public void tearDown() {
        expertsRepository.deleteAll();
        orgsRepository.deleteAll();
    }

    private void createTestOrg() {
        Map<String, Object> orgData = new HashMap<>();
        orgData.put("orgName", ORG_NAME);
        orgData.put("website", "foo");
        orgData.put("industry", "foo");

        postRequest(orgData, "/orgs/create");
    }

    private Response createExpert(String expertName, String organizationName) {
        Map<String, Object> expertData = new HashMap<>();
        expertData.put("expertName", expertName);
        expertData.put("orgName", organizationName);

        return postRequest(expertData, BASE_PATH);
    }

    private Response postRequest(Map<String, Object> requestData, String path) {
        return given()
                .contentType(CONTENT_TYPE_JSON)
                .body(requestData)
                .when()
                .post(path);
    }
}
