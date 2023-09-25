package edu.kmaooad.capstone23.jobs.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToCompetences;
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
public class DeleteRelateJobToCompetencesControllerTest {

    @Inject
    CommandHandler<CreateJob, JobCreated> CreateJobHandler;

    @Inject
    CommandHandler<RelateJobToCompetences, CompetenceRelated> handlerForRelation;

    @Inject
    CommandHandler<CreateSkill, SkillCreated> handlerForCompetences;

    @Test
    @DisplayName("Delete Relate Job To Competences: existed relation, valid input")
    public void testBasicJobCompetencesConnectionRemoving() {

        CreateJob command = new CreateJob("TeacherUnique", true);
        Result<JobCreated> result = CreateJobHandler.handle(command);

        CreateSkill commandSkill = new CreateSkill();
        commandSkill.setSkillName("Tyyyy");
        Result<SkillCreated> resultSkill = handlerForCompetences.handle(commandSkill);

        RelateJobToCompetences commandRelateJobToCompetences = new RelateJobToCompetences();
        commandRelateJobToCompetences.setJobId(result.getValue().getJobId());
        commandRelateJobToCompetences.setCompetenceId(resultSkill.getValue().getSkill());
        Result<CompetenceRelated> resultRelateJobToCompetences = handlerForRelation.handle(commandRelateJobToCompetences);

        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("jobId", resultRelateJobToCompetences.getValue().getJobId().toHexString());
        jsonAsMap.put("competenceId", resultRelateJobToCompetences.getValue().getCompetenceId().toHexString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/delete_relate_to_competences")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete Relate Job To Competences: not existed Competences")
    public void testJobInvalidRemoving() {

        ObjectId id = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");

        CreateJob command = new CreateJob("TeacherUnique", true);
        Result<JobCreated> result = CreateJobHandler.handle(command);

        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("jobId", result.getValue().getJobId().toHexString());
        jsonAsMap.put("competenceId", id);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/delete_relate_to_competences")
                .then()
                .statusCode(400);
    }

}