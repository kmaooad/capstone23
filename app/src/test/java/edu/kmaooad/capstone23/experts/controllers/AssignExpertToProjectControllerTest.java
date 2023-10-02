package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
@QuarkusTest
public class AssignExpertToProjectControllerTest {

    private static final String ORG_NAME = "Random Org";
    private Org org;
    private Department department;
    @Inject
    ProjsRepository projsRepository;
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    ExpertsRepository expertsRepository;

    @BeforeEach
    public void setUp() {
        createTestOrg();
    }

    @Test
    @DisplayName("Assign Expert to project: Basic")
    public void testAssignExpertToProject() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("expertId", createTestExpert().toString());
        jsonAsMap.put("projectId", createTestProject().toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/assign_expert_to_project")
                .then()
                .statusCode(200);
    }

    private ObjectId createTestProject() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "OOK");
        jsonAsMap.put("description", "This project is for students");
        jsonAsMap.put("skills", List.of("5f7e47fc8e1f7112d73c92a1"));
        jsonAsMap.put("skillSets", List.of("1a4cd132b123a1aa3bc2d142"));

        String objectId = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/projs/create")
                .then()
                .statusCode(200)
                .extract()
                .path("projId");

        return new ObjectId(objectId);
    }

    private ObjectId createTestExpert() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertName", "New Super Random Expert");
        jsonAsMap.put("orgName", ORG_NAME);

        String objectId =  given()
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
