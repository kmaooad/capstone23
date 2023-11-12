package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.competences.dal.MongoProjectRepository;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
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
    private static final String BASE_PATH = "/experts/assign_expert_to_project";
    private static final String CONTENT_TYPE = "application/json";

    private ObjectId project;
    private ObjectId expert;

    @Inject
    MongoProjectRepository projectRepository;
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    ExpertsRepository expertsRepository;

    @BeforeEach
    public void setUp() {
        createTestOrg();
        project = createTestProject();
        expert = createTestExpert();
    }

    @Test
    @DisplayName("Assign Expert to project: Basic")
    public void whenAssigningExpertToProject_thenStatus200() {
        assignExpertToProject(expert.toString(), project.toString(), 200);
    }

    @Test
    @DisplayName("Invalid ProjectId")
    public void whenAssigningExpertToProjectWithInvalidProjectId_thenStatus400() {
        assignExpertToProject(expert.toString(), "Random wrong id", 400);
    }

    @Test
    @DisplayName("Invalid ExpertId")
    public void whenAssigningExpertToProjectWithInvalidExpertId_thenStatus400() {
        assignExpertToProject("Invalid expert id", project.toString(), 400);
    }

    @Test
    @DisplayName("Expert already assigned to project")
    public void whenAssigningAlreadyAssignedExpertToProject_thenStatus400() {
        ObjectId expertWithProject = createTestExpertWithProject();
        assignExpertToProject(expertWithProject.toString(), project.toString(), 400);
    }

    // Common method to assign expert to project and assert the status code
    private void assignExpertToProject(String expertId, String projectId, int statusCode) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertId", expertId);
        jsonAsMap.put("projectId", projectId);

        given()
                .contentType(CONTENT_TYPE)
                .body(jsonAsMap)
                .when()
                .post(BASE_PATH)
                .then()
                .statusCode(statusCode);
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

      private ObjectId createTestExpertWithProject() {
        Expert expert = new Expert();
        expert.name = "Test Name";
        expert.org = orgsRepository.findByName(ORG_NAME);
        expert.projects = List.of(projectRepository.findById(project));
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
