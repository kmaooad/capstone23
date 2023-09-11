package edu.kmaooad.capstone23.orgs.controllers;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobsRepository;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateJobControllerTest {

    @Inject
    private JobsRepository repository;
    private ObjectId idToUpdate;

    @BeforeEach
    void setUp(){
        Job job = new Job();
        job.name = "testJob";
        repository.insert(job);
        idToUpdate = job.id;
    }
    
    @Test
    @DisplayName("Update Job: Basic")
    public void testBasicJobUpdate() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("jobId", idToUpdate.toString());
        jsonAsMap.put("jobName", "newJobName");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/update")
                .then()
                .statusCode(200);
    }
}
