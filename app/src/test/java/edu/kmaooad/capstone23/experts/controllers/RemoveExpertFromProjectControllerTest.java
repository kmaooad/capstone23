package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RemoveExpertFromProjectControllerTest {
    private Org org;
    private Expert expert;
    private Project project;
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    ProjsRepository projsRepository;
    @Inject
    ExpertsRepository expertsRepository;

    @BeforeEach
    public void setUp() {
        createTestOrg();
        createTestProject();
        createTestExpert();
    }

    @Test
    @DisplayName("Remove Expert From Project: Basic")
    public void testExpertFromProjectRemoval() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertId", expert.id.toHexString());
        jsonAsMap.put("projectId", project.id.toHexString());

        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/remove_expert_from_project")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Remove Expert From Project: Removal Of Already Removed Expert")
    public void testRemovedExpertFromProjectRemoval() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertId", expert.id.toHexString());
        jsonAsMap.put("projectId", project.id.toHexString());

        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/remove_expert_from_project")
                .then()
                .statusCode(200);

        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/remove_expert_from_project")
                .then()
                .statusCode(400);
    }

    @AfterEach
    public void tearDown() {
        projsRepository.deleteById(project.id);
        expertsRepository.deleteById(expert.id);
        orgsRepository.deleteById(org.id);
    }

    private void createTestOrg() {
        org = new Org();
        org.name = "Brovary Club";
        org.industry = "Some random industry";
        org.website = "Some random website";
        org.description = "The club of an awesome city of Brovary";
        org.emailDomain = "bro.gachi.club";
        org.hiringStatus = "HIRING";
        org.jobs = new ArrayList<>();
        org.isActive = true;
        orgsRepository.insert(org);
    }

    private void createTestProject() {
        project = new Project();
        project.name = "Faina Ukraina";
        project.description = "A fascinating Ukrainian TV series";
        project.skills = new ArrayList<>();
        project.skillSets = new ArrayList<>();
        projsRepository.insert(project);
    }

    private void createTestExpert() {
        expert = new Expert();
        expert.name = "Test Name";
        expert.org = org;
        expert.departments = new ArrayList<>();
        expert.projects = new ArrayList<>();
        expert.projects.add(project);
        expertsRepository.insert(expert);
    }
}
