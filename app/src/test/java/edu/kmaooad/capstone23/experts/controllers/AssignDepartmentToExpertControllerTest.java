package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AssignDepartmentToExpertControllerTest {
    private static final String ORG_NAME = "Persyk Inc";
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    DepartmentsRepository departmentsRepository;
    @Inject
    ExpertsRepository expertsRepository;

    @Test
    @DisplayName("Assign Department To Expert: Basic")
    public void testAssignDepartmentToExpert() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("expertId", createTestExpert());
        jsonAsMap.put("departmentId", creatTestDepartment());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/departments/assign")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Assign Department To Expert: Non-Existent Expert")
    public void testAssignDepartmentToInvalidExpert() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("expertId", "64fe000000000a0000000000");
        jsonAsMap.put("departmentId", creatTestDepartment());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/departments/assign")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Assign Department To Expert: Non-Existent Department")
    public void testAssignInvalidDepartmentToExpert() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("expertId", createTestExpert());
        jsonAsMap.put("departmentId", "64fe000000000a0000000000");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/departments/assign")
                .then()
                .statusCode(400);
    }

    @AfterEach
    void tearDown() {
        expertsRepository.deleteAll();
        departmentsRepository.deleteAll();
        orgsRepository.deleteAll();
    }

    private String createTestOrg() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", ORG_NAME);
        jsonAsMap.put("industry", "Rand industry");
        jsonAsMap.put("website", "Rand website");

        return given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/create")
                .then()
                .statusCode(200)
                .extract()
                .path("orgId");
    }

    private String creatTestDepartment() {
        if (orgsRepository.findByName(ORG_NAME) == null) {
            createTestOrg();
        }

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "FI");
        jsonAsMap.put("description", "Faculty of Informatics");
        jsonAsMap.put("parent", ORG_NAME);

        return given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/create")
                .then()
                .statusCode(200)
                .extract()
                .path("id");
    }

    private String createTestExpert() {
        createTestOrg();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertName", "Petro Mostavchuk");
        jsonAsMap.put("orgName", ORG_NAME);

        return given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/create")
                .then()
                .statusCode(200)
                .extract()
                .path("expertId");
    }
}