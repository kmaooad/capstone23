package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
@QuarkusTest
public class AssignExpertToProjectControllerTest {

    private static final String ORG_NAME = "Random Org";
    private ObjectId project;
    @Inject
    ProjsRepository projsRepository;
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    ExpertsRepository expertsRepository;

    @BeforeEach
    public void setUp() {
        createTestOrg();
        project = createTestProject();
    }

    @Test
    @DisplayName("Assign Expert to project that he/she already have")
    public void testExpertAssignedToProjAlready() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("expertId", createTestExpertWithProject().toString());
        jsonAsMap.put("projectId", project.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/assign_expert_to_project")
                .then()
                .statusCode(400);
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


    private ObjectId createTestExpertWithProject() {
        Expert expert = new Expert();
        expert.name = "Test Name";
        expert.org = orgsRepository.findByName(ORG_NAME);
        expert.projects = List.of(projsRepository.findById(project));
        expertsRepository.insert(expert);

        return expert.id;
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
