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
    @Inject
    CommandHandler<CreateJob, JobCreated> CreateJobHandler;

    @Test
    @DisplayName("Create Skill: Basic")
    public void testBasicOrgCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillName", "food");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skills/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Relate Job To Competences: existed job")
    public void testBasicJobCompetencesConnectionCreation() {

        CreateJob command = new CreateJob("TeacherUnique", true);
        Result<JobCreated> result = CreateJobHandler.handle(command);

        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("jobId", result.getValue().getJobId().toHexString());
        jsonAsMap.put("skillName", "food");
        // jsonAsMap.put("courseName", "Course");


        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/relate_to_competences")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Relate Job To Competences: notExisted job")
    public void testNotExistedJobCompetencesConnectionCreation() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        ObjectId id = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");
        jsonAsMap.put("jobId", id);
        jsonAsMap.put("skillName", "food");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/relate_to_competences")
                .then()
                .statusCode(400);
    }
}
