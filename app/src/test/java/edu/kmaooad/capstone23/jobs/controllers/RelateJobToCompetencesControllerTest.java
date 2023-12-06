package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
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
public class RelateJobToCompetencesControllerTest {

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String RELATE_JOB_TO_COMPETENCES_ENDPOINT = "/jobs/relate_to_competences";

    @Inject
    CommandHandler<CreateJob, JobCreated> createJobHandler;

    @Test
    @DisplayName("Relate Job To Competences: existed job")
    public void testBasicJobCompetencesConnectionCreation() {
        ObjectId jobId = createJobAndGetId("TeacherUnique");
        relateJobToCompetence(jobId, "food", 200);
    }

    @Test
    @DisplayName("Relate Job To Competences: not existed job")
    public void testNotExistedJobCompetencesConnectionCreation() {
        ObjectId nonExistentJobId = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");
        relateJobToCompetence(nonExistentJobId, "food", 400);
    }

    @Test
    @DisplayName("Relate Job To Competences: not existed competence")
    public void testNotExistedCompetencesConnectionCreation() {
        ObjectId jobId = createJobAndGetId("TeacherUnique");
        ObjectId nonExistentCompetenceId = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");
        relateJobToCompetence(jobId, nonExistentCompetenceId.toHexString(), 400);
    }

    private ObjectId createJobAndGetId(String jobName) {
        CreateJob command = new CreateJob(jobName, true);
        Result<JobCreated> result = createJobHandler.handle(command);
        return result.getValue().getJobId();
    }

    private void relateJobToCompetence(ObjectId jobId, String skillName, int expectedStatusCode) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("jobId", jobId.toHexString());
        jsonAsMap.put("skillName", skillName);

        performPostRequest(RELATE_JOB_TO_COMPETENCES_ENDPOINT, jsonAsMap, expectedStatusCode);
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
