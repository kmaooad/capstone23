package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateProj;
import edu.kmaooad.capstone23.competences.events.ProjCreated;
import edu.kmaooad.capstone23.experts.commands.AssignExpertToProject;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.events.ExpertAssigned;
import edu.kmaooad.capstone23.experts.events.ExpertAssignedToProject;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class AssignExpertToProjectHandlerTest {
    @Inject
    CommandHandler<CreateOrg, OrgCreated> orgHandler;
    @Inject
    CommandHandler<CreateProj, ProjCreated> projCreatedCommandHandler;
    @Inject
    CommandHandler<AssignExpertToProject, ExpertAssignedToProject> assignedExpertToProjectCommandHandler;
    @Inject
    CommandHandler<CreateExpert, ExpertCreated> expertCreatedCommandHandler;

    @Test
    public void testSuccessfulHandling() {
        AssignExpertToProject assignExpertToProject = new AssignExpertToProject();
        assignExpertToProject.setExpertId(createTestExpert());
        assignExpertToProject.setProjectId(createTestProj());

        Result<ExpertAssignedToProject> result = assignedExpertToProjectCommandHandler.handle(assignExpertToProject);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getMemberId().isEmpty());
    }
  
    @Test
    public void testEmptyExpert() {
        AssignExpertToProject assignExpertToProject = new AssignExpertToProject();
        assignExpertToProject.setProjectId(createTestProj());

        Result<ExpertAssignedToProject> result = assignedExpertToProjectCommandHandler.handle(assignExpertToProject);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }
  
    private ObjectId createTestExpert() {
        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName("Super Duper Create Team");
        orgCommand.industry = "Some random industry";
        orgCommand.website = "Some random website";
        orgHandler.handle(orgCommand);

        CreateExpert expertCommand = new CreateExpert();
        expertCommand.setExpertName("Test Name");
        expertCommand.setOrgName("Super Duper Create Team");

        return new ObjectId(expertCreatedCommandHandler.handle(expertCommand).getValue().getExpertId());
    }

    private ObjectId createTestProj() {
        var command = new CreateProj();
        command.setName("SomeProject");
        command.setDescription("Some description of some project");
        command.setSkills(List.of(
                new ObjectId("5f7e47fc8e1f7112d73c92a1")
        ));

        command.setSkillSets(List.of(
                new ObjectId("1a4cd132b123a1aa3bc2d142")
        ));

        return new ObjectId(projCreatedCommandHandler.handle(command).getValue().projId());
    }
}
