package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillSetCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateSkillSetHandlerTest {
    @BeforeAll
    static void deleteAllData() {
        SkillsRepository skillsRepository = new SkillsRepository();
        skillsRepository.deleteAll();
    }

    @Inject
    CommandHandler<CreateSkillSet, SkillSetCreated> handler;

    @Test
    void testSuccessfulHandling() {
        CreateSkillSet command = new CreateSkillSet();
        command.setSkillSetName("SoftSkills");

        Result<SkillSetCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getSkillSetId().isEmpty());
    }

    @Test
    void testNameValidation() {
        CreateSkillSet command = new CreateSkillSet();
        command.setSkillSetName("SoftSkills_1");

        Result<SkillSetCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
