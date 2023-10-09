package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromProject;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromProject;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RemoveExpertFromProjectHandlerTest {
    private Org org;
    private Project project;
    private Expert expert;
    @Inject
    CommandHandler<RemoveExpertFromProject, ExpertRemovedFromProject> removeHandler;
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    ProjsRepository projsRepository;
    @Inject
    ExpertsRepository expertsRepository;

    @BeforeEach
    public void setUp() {
        createTestOrg();
        createTestProject();
        createTestExpert();
    }

    @Test
    @DisplayName("Remove Expert From Member Handler: Basic")
    public void testSuccessfulHandling() {
        RemoveExpertFromProject command = new RemoveExpertFromProject();
        command.setExpertId(expert.id);
        command.setProjectId(project.id);

        Result<ExpertRemovedFromProject> result = removeHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertTrue(expertsRepository
                .findById(new ObjectId(result.getValue().getExpertId()))
                .projects.isEmpty());
    }

    @Test
    @DisplayName("Remove Expert From Member Handler: Invalid Expert")
    public void testInvalidExpertHandling() {
        RemoveExpertFromProject command = new RemoveExpertFromProject();
        command.setExpertId(new ObjectId( "64fe000000000a0000000000"));
        command.setProjectId(project.id);

        Result<ExpertRemovedFromProject> result = removeHandler.handle(command);

        Assertions.assertEquals(ErrorCode.NOT_FOUND, result.getErrorCode());
    }

    @AfterEach
    public void tearDown() {
        projsRepository.deleteById(project.id);
        expertsRepository.deleteById(expert.id);
        orgsRepository.deleteById(org.id);
    }

    private void createTestOrg() {
        org = new Org();
        org.name = "Brovary Club";
        org.industry = "Some random industry";
        org.website = "Some random website";
        org.description = "The club of an awesome city of Brovary";
        org.emailDomain = "bro.gachi.club";
        org.hiringStatus = "HIRING";
        org.jobs = new ArrayList<>();
        org.isActive = true;
        orgsRepository.insert(org);
    }

    private void createTestProject() {
        project = new Project();
        project.name = "Faina Ukraina";
        project.description = "A fascinating Ukrainian TV series";
        project.skills = new ArrayList<>();
        project.skillSets = new ArrayList<>();
        projsRepository.insert(project);
    }

    private void createTestExpert() {
        expert = new Expert();
        expert.name = "Test Name";
        expert.org = org;
        expert.departments = new ArrayList<>();
        expert.projects = new ArrayList<>();
        expert.projects.add(project);
        expertsRepository.insert(expert);
    }
}