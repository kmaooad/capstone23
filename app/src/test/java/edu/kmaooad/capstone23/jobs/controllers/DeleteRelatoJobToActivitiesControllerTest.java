package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToCompetences;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.CompetenceRelated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteRelateJobToActivitiesControllerTest {

    @Inject
    CommandHandler<CreateJob, JobCreated> CreateJobHandler;
    @Inject
    CommandHandler<RelateJobToActivities, ActivityRelated> handlerForRelation;
    @Inject
    CommandHandler<CreateCourse, CourseCreated> handlerForActivities;

    @Test
    @DisplayName("Delete Relate Job To Activities: existed relation, valid input")
    public void testBasicJobActivitiesConnectionRemoving() {

        CreateJob command = new CreateJob("TeacherUnique", true);
        Result<JobCreated> result = CreateJobHandler.handle(command);

        CreateCourse commandCourse = new CreateCourse();
        commandCourse.setName("Math");
        Result<CourseCreated> resultCourse = handlerForActivities.handle(commandCourse);

        RelateJobToActivities commandRelateJobToActivities = new RelateJobToActivities();
        commandRelateJobToActivities.setJobId(result.getValue().getJobId());
        ObjectId idCourse = new ObjectId(resultCourse.getValue().getId());
        commandRelateJobToActivities.setActivityId(idCourse);
        Result<ActivityRelated> resultRelateJobToActivities = handlerForRelation.handle(commandRelateJobToActivities);

        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("jobId", resultRelateJobToActivities.getValue().getJobId().toHexString());
        jsonAsMap.put("activityId", resultRelateJobToActivities.getValue().getActivityId().toHexString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/delete_relation_to_activities")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete Relate Job To Activities: not existed Activities")
    public void testActivityInvalidRemoving() {

        ObjectId id = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");

        CreateJob command = new CreateJob("TeacherUnique", true);
        Result<JobCreated> result = CreateJobHandler.handle(command);

        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("jobId", result.getValue().getJobId().toHexString());
        jsonAsMap.put("activityId", id);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/delete_relation_to_activities")
                .then()
                .statusCode(400);
    }
}