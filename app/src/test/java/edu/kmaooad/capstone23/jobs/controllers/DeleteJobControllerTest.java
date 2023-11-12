package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.kmaooad.capstone23.common.Result;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import jakarta.inject.Inject;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteJobControllerTest {

    @Inject
    CommandHandler<CreateJob, JobCreated> handler;
    private static final String DELETE_JOB_ENDPOINT = "/jobs/delete";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final int STATUS_OK = 200;
    private static final int STATUS_BAD_REQUEST = 400;

    @Test
    @DisplayName("Delete job: valid input")
    public void testBasicJobDeleting() {
        Result<JobCreated> result = handler.handle(new CreateJob("Teacher", true));
        String jobId = result.getValue().getJobId().toHexString();

        sendDeleteJobRequest(jobId)
                .statusCode(STATUS_OK);
    }

    @Test
    @DisplayName("Delete job: invalid input")
    public void testJobDeletingWithInvalidInput() {
        String jobId = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa").toHexString();

        sendDeleteJobRequest(jobId)
                .statusCode(STATUS_BAD_REQUEST);
    }

    private io.restassured.response.ValidatableResponse sendDeleteJobRequest(String jobId) {
        Map<String, Object> jsonAsMap = Map.of("jobId", jobId);

        return given()
                .contentType(CONTENT_TYPE_JSON)
                .body(jsonAsMap)
                .when()
                .post(DELETE_JOB_ENDPOINT)
                .then();
    }
}

