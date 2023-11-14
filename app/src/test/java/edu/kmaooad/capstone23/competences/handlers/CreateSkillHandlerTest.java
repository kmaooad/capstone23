package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
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
        command2.setParentSkill(result.getValue().getSkill().toHexString());

        Result<SkillCreated> result2 = handler.handle(command2);

        Assertions.assertTrue(result2.isSuccess());
        Assertions.assertNotNull(result2.getValue());
        Assertions.assertNotNull(result2.getValue().getSkill());
    }


    @Test
    void testUnsuccessfulRelationHandling() {
        var command2 = new CreateSkill();
        command2.setSkillName("fruit");
        command2.setParentSkill("5faabb2e8d6c4b3f3e217465");

        Result<SkillCreated> result2 = handler.handle(command2);

        Assertions.assertFalse(result2.isSuccess());
    }
}
