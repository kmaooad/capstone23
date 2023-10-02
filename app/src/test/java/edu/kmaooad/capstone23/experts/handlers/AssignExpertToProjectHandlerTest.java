package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateProj;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.competences.events.ProjCreated;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.experts.commands.AssignExpertToProject;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertAssigned;
import edu.kmaooad.capstone23.experts.events.ExpertAssignedToProject;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class AssignExpertToProjectHandlerTest {

    private Org org;
    private ObjectId project;

    @Inject
    OrgsRepository orgsRepository;
    @Inject
    ProjsRepository projsRepository;
    @Inject
    ExpertsRepository expertsRepository;
    @Inject
    CommandHandler<CreateOrg, OrgCreated> orgHandler;
    @Inject
    CommandHandler<CreateProj, ProjCreated> projCreatedCommandHandler;
    @Inject
    CommandHandler<AssignExpertToProject, ExpertAssignedToProject> assignedExpertToProjectCommandHandler;
    @Inject
    CommandHandler<CreateExpert, ExpertCreated> expertCreatedCommandHandler;

    @BeforeEach
    public void setUp() {
        org = createTestOrg();
        project = createTestProj();
    }

    @Test
    public void testExistingProjectInExpert() {
        AssignExpertToProject assignExpertToProject = new AssignExpertToProject();
        assignExpertToProject.setExpertId(createTestExpertWithProj());
        assignExpertToProject.setProjectId(project);

        Result<ExpertAssignedToProject> result = assignedExpertToProjectCommandHandler.handle(assignExpertToProject);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.CONFLICT);
    }

    private Org createTestOrg() {
        Org org = new Org();
        org.name = "Brovary Club";
        org.industry = "Some random industry";
        org.website = "Some random website";
        orgsRepository.insert(org);
        return org;
    }

    private ObjectId createTestExpertWithProj() {
        Expert expert = new Expert();
        expert.name = "Test Name";
        expert.org = org;
        expert.projects = List.of(projsRepository.findById(project));
        expertsRepository.insert(expert);

        return expert.id;
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
