package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkillSet;
import edu.kmaooad.capstone23.competences.events.SkillSetCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@QuarkusTest
public class CreateSkillSetHandlerTest {

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
    void testNotSuccessfulHandling() {
        CreateSkillSet command = new CreateSkillSet();
        command.setSkillSetName("SoftSkills");
        var list = new ArrayList<ObjectId>();
        list.add(new ObjectId());
        command.setSkillIds(list);

        Result<SkillSetCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    void testNameValidation() {
        CreateSkillSet command = new CreateSkillSet();
        command.setSkillSetName("SoftSkills_1");

        Result<SkillSetCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
