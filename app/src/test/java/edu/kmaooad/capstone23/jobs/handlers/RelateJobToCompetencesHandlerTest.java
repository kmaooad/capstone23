package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToCompetences;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.CompetenceRelated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
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
}
