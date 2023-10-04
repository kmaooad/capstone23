package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class RelateJobToCompetencesHandlerTest {
    @Inject
    CommandHandler<CreateJob, JobCreated> createJobHandler;
    @Inject
    CommandHandler<CreateSkill, SkillCreated> createSkillHandler;
    @Inject
    CommandHandler<RelateJobToCompetences, CompetenceRelated> relateHandler;

    private  Result<JobCreated> result;
    @BeforeEach
    void setUp() {
        CreateJob command = new CreateJob("IT teacher", true);
        result = createJobHandler.handle(command);
    }

    @Test
    void testSuccessfulHandling(){
        CreateJob command = new CreateJob("TeacherUnique", true);
        Result<JobCreated> result = createJobHandler.handle(command);

        CreateSkill skillCommand = new CreateSkill();
        skillCommand.setSkillName("Python Programming");
        Result<SkillCreated> skillResult = createSkillHandler.handle(skillCommand);

        RelateJobToCompetences relateCommand = new RelateJobToCompetences();
        relateCommand.setJobId(result.getValue().getJobId());
        relateCommand.setCompetenceId(skillResult.getValue().getSkill());

        Result<CompetenceRelated> competenceRelatedResult = relateHandler.handle(relateCommand);

        Assertions.assertTrue(competenceRelatedResult.isSuccess());
        Assertions.assertNotNull(competenceRelatedResult.getValue());
        Assertions.assertTrue(competenceRelatedResult.getValue().getJobId().equals(result.getValue().getJobId()));

    }

    @Test
    void testHandlingUnExistedSkill() {
        RelateJobToCompetences relateJobToCompetences = new RelateJobToCompetences();
        relateJobToCompetences.setJobId(result.getValue().getJobId());
        ObjectId nonexistentCompetenceId = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");
        relateJobToCompetences.setCompetenceId(nonexistentCompetenceId);

        Result<CompetenceRelated> activityRelatedResult = relateHandler.handle(relateJobToCompetences);

        Assertions.assertFalse(activityRelatedResult.isSuccess());
        Assertions.assertNull(activityRelatedResult.getValue());
    }
}
