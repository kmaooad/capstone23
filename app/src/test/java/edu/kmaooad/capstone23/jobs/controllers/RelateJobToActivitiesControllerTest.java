package edu.kmaooad.capstone23.jobs.controllers;


import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RelateJobToActivitiesControllerTest {

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String RELATE_JOB_TO_ACTIVITIES_ENDPOINT = "/jobs/relate_to_activities";

    @Inject
    CommandHandler<RelateJobToActivities, ActivityRelated> handler;

    @Inject
    CommandHandler<CreateJob, JobCreated> CreateJobHandler;

    @Inject
    CommandHandler<CreateCourse, CourseCreated> CreateActivityHandler;

    @Inject
    CourseRepository courseRepository;

    private ObjectId idToUpdate;

    @BeforeEach
    void setUp() {
        Course course = new Course();
        course.name = "Initial Course";
        courseRepository.insert(course);
        idToUpdate = course.id;
    }

    @Test
    @DisplayName("Relate Job To Activities: existed job")
    public void testBasicJobActivityConnectionCreation() {
        Result<JobCreated> result = createJob("TeacherUnique", true);
        relateJobToActivity(result.getValue().getJobId(), idToUpdate, 200);
    }

    @Test
    @DisplayName("Relate Job To Activities: notExisted job")
    public void testNotExistedJobActivityConnectionCreation() {
        ObjectId nonExistentJobId = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");
        relateJobToActivity(nonExistentJobId, idToUpdate, 400);
    }

    private Result<JobCreated> createJob(String name, boolean isActive) {
        CreateJob command = new CreateJob(name, isActive);
        return CreateJobHandler.handle(command);
    }

    private void relateJobToActivity(ObjectId jobId, ObjectId activityId, int expectedStatusCode) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("jobId", jobId.toHexString());
        jsonAsMap.put("activityId", activityId.toHexString());

        performPostRequest(RELATE_JOB_TO_ACTIVITIES_ENDPOINT, jsonAsMap, expectedStatusCode);
    }

    private void performPostRequest(String endpoint, Map<String, Object> jsonAsMap, int expectedStatusCode) {
        given()
                .contentType(CONTENT_TYPE_JSON)
                .body(jsonAsMap)
                .when()
                .post(endpoint)
                .then()
                .statusCode(expectedStatusCode);
    }
}
