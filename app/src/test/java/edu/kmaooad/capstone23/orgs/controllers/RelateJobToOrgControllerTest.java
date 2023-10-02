package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RelateJobToOrgControllerTest {
    private String orgId;

    private String jobId;
    @Inject
    OrgsRepository orgsRepository;

    @Inject
    JobRepository jobRepository;

    @BeforeEach
    void setUp() {
        jobRepository.deleteAll();
        orgsRepository.deleteAll();
        Org org = new Org();
        org.name = "Initial Org";
        org.description = "Initial Org Description";

        org.jobs = new ArrayList<>();


        Job job = new Job();
        job.name = "Initial Job";
        job.active = true;
        jobRepository.insert(job);

        org.jobs.add(job.id.toString());

        orgsRepository.insert(org);


        orgId = org.id.toString();

        jobId = job.id.toString();
    }


    @Test
    @DisplayName("Relate Job To Department: existed job")
    public void testBasicJobOrgConnectionCreation() {

        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("jobId", jobId);
        jsonAsMap.put("orgId", orgId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/relate-job-to-department")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("Relate Job To Department: notExisted job")
    public void testNotExistedJobOrgConnectionCreation() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        String id = "aaaaaaaaaaaaaaaaaaaaaaaa";
        jsonAsMap.put("jobId", id);
        jsonAsMap.put("orgId", orgId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/relate-job-to-department")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Relate Job To Department: notExisted department")
    public void testNotExistedOrgJobConnectionCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        String id = "64fbb243275c1111167b87a3";
        jsonAsMap.put("jobId", jobId);
        jsonAsMap.put("orgId", id);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/relate-job-to-department")
                .then()
                .statusCode(400);
    }
}
