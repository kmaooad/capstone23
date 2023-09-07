package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateSkillHandlerTest {

    @Inject
    CommandHandler<CreateSkill, SkillCreated> handler;

    @Test
    void testSuccessfulHandling() {
        var command = new CreateSkill();
        command.setSkillName("food");

        Result<SkillCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getSkill());
    }

    @Test
    void testSuccessfulRelationHandling() {
        var command = new CreateSkill();
        command.setSkillName("food");

        Result<SkillCreated> result = handler.handle(command);

        Assertions.assertNotNull(result.getValue().getSkill());

        var command2 = new CreateSkill();
        command2.setSkillName("fruit");
        command2.setParentSkill(result.getValue().getSkill());

        Result<SkillCreated> result2 = handler.handle(command2);

        Assertions.assertTrue(result2.isSuccess());
        Assertions.assertNotNull(result2.getValue());
        Assertions.assertNotNull(result2.getValue().getSkill());
    }
}
