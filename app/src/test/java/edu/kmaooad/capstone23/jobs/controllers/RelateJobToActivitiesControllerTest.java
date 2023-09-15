package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
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

    @Inject
    CommandHandler<RelateJobToActivities, ActivityRelated> handler;

    @Inject
    CommandHandler<CreateJob, JobCreated> CreateJobHandler;

    @Inject
    CommandHandler<CreateCourse, CourseCreated> CreateActivityHandler;
    private ObjectId idToUpdate;

    @Inject
    CourseRepository courseRepository;

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

        CreateJob command = new CreateJob("TeacherUnique", true);
        Result<JobCreated> result = CreateJobHandler.handle(command);

        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("jobId", result.getValue().getJobId().toHexString());
        jsonAsMap.put("activityId", idToUpdate.toString());
       // jsonAsMap.put("courseName", "Course");


        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/relate_to_activities")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Relate Job To Activities: notExisted job")
    public void testNotExistedJobActivityConnectionCreation() {

//        CreateJob command = new CreateJob("TeacherUnique", true);
//        Result<JobCreated> result = CreateJobHandler.handle(command);

        Map<String, Object> jsonAsMap = new HashMap<>();
        ObjectId id = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");
        jsonAsMap.put("jobId", id);
        jsonAsMap.put("activityId", idToUpdate.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/relate_to_activities")
                .then()
                .statusCode(400);
    }
}
