package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.DeleteJob;
import edu.kmaooad.capstone23.jobs.commands.DeleteRelateJobToCompetences;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToCompetences;
import edu.kmaooad.capstone23.jobs.events.CompetenceRelated;
import edu.kmaooad.capstone23.jobs.events.CompetenceUnrelated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.jobs.events.JobDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteRelateJobToCompetencesHandlerTest {

    @Inject
    CommandHandler<CreateJob, JobCreated> createJobHandler;
    @Inject
    CommandHandler<CreateSkill, SkillCreated> createSkillHandler;
    @Inject
    CommandHandler<RelateJobToCompetences, CompetenceRelated> relateHandler;

    @Inject
    CommandHandler<DeleteRelateJobToCompetences, CompetenceUnrelated> handlerForDel;
    @Test
    void testSuccessfulDeleteHandling(){
        CreateJob command = new CreateJob("TeacherUnique", true);
        Result<JobCreated> result = createJobHandler.handle(command);

        CreateSkill skillCommand = new CreateSkill();
        skillCommand.setSkillName("Java Programming");
        Result<SkillCreated> skillResult = createSkillHandler.handle(skillCommand);

        RelateJobToCompetences relateCommand = new RelateJobToCompetences();
        relateCommand.setJobId(result.getValue().getJobId());
        relateCommand.setCompetenceId(skillResult.getValue().getSkill());
        Result<CompetenceRelated> competenceRelatedResult = relateHandler.handle(relateCommand);

        DeleteRelateJobToCompetences commandForDel = new DeleteRelateJobToCompetences();
        commandForDel.setJobId(result.getValue().getJobId());
        commandForDel.setCompetenceId(skillResult.getValue().getSkill());
        Result<CompetenceUnrelated> resultForDel = handlerForDel.handle(commandForDel);

        Assertions.assertTrue(resultForDel.isSuccess());
        Assertions.assertNotNull(resultForDel.getValue());
        Assertions.assertTrue(resultForDel.getValue().getJobId().equals(result.getValue().getJobId()));

    }
}