package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RelateJobToActivitiesControllerTest {

    @Inject
    CommandHandler<RelateJobToActivities, ActivityRelated> handler;

    @Inject
    CommandHandler<CreateJob, JobCreated> CreateJobHandler;

    @Inject
    CommandHandler<CreateCourse, CourseCreated> CreateActivityHandler;
    @Test
    @DisplayName("Relate Job To Activities: existed job")
    public void testBasicJobCreation() {

        CreateJob command = new CreateJob("Teacher", true);
        Result<JobCreated> result = CreateJobHandler.handle(command);

        CreateCourse commandCourse = new CreateCourse();
        Result<CourseCreated> resultCourse = CreateActivityHandler.handle(commandCourse);

//        Map<String, Object> jsonAsMap = new HashMap<>();
//        jsonAsMap.put("jobId", result.getValue().getJobId().toHexString());
//
//        given()
//                .contentType("application/json")
//                .body(jsonAsMap)
//                .when()
//                .post("/jobs/delete")
//                .then()
//                .statusCode(200);
    }
}
