package edu.kmaooad.capstone23.orgs.jobs.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class DeactivateJobControllerTest {
    @Inject
    CommandHandler<CreateJob, JobCreated> handler;
    @Test
    @DisplayName("Activate job: valid input")
    public void testBasicJobActivation() {
        CreateJob command = new CreateJob("Teacher", true);
        Result<JobCreated> result = handler.handle(command);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("jobId", result.getValue().getJobId().toHexString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/activate")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Activate job: invalid input")
    public void testJobActivationWithInvalidInput() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        ObjectId id = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");
        jsonAsMap.put("jobId", id);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/activate")
                .then()
                .statusCode(400);
    }
}