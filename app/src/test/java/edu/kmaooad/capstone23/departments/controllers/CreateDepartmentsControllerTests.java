package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateDepartmentsControllerTests {

    @Inject
    OrgsRepository orgsRepository;

    @BeforeEach
    void setUp() {
        createParentOrg();
    }

    private void createParentOrg() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "NaUKMA");
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

    @AfterEach
    public void tearDown() {
        orgsRepository.deleteAll();
    }

    @Test
    @DisplayName("Create Departments: Basic")
    public void testBasicDeptsCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "FI");
        jsonAsMap.put("description", "Faculty of Informatics");
        jsonAsMap.put("parent", "NaUKMA");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Departments: Name validation")
    public void testDeptsCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Kma0oad_2023");
        jsonAsMap.put("description", "Faculty of Informatics");
        jsonAsMap.put("parent", "NaUKMA");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/create")
                .then()
                .statusCode(400);
    }
}