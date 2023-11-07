package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.commands.UpdateSkill;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.competences.events.SkillUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@QuarkusTest
public class UpdateSkillHandlerTest {

    @Inject
    CommandHandler<UpdateSkill, SkillUpdated> updateHandler;

    @Inject
    CommandHandler<CreateSkill, SkillCreated> createHandler;

    @Test
    void testSuccessfulHandling() {
        var command = new CreateSkill();
        command.setSkillName("food");

        Result<SkillCreated> result = createHandler.handle(command);

        Result<SkillUpdated> result2 = changeSkill(result.getValue().getSkill(), Optional.empty());

        Assertions.assertTrue(result2.isSuccess());
        Assertions.assertNotNull(result2.getValue());
        Assertions.assertNotNull(result2.getValue().getSkill());
    }

    @Test
    void testBadParentIdHandling() {
        var command = new CreateSkill();
        command.setSkillName("food");

        Result<SkillCreated> result = createHandler.handle(command);

        Result<SkillUpdated> result2 = changeSkill(result.getValue().getSkill(), Optional.of(new ObjectId()));

        Assertions.assertFalse(result2.isSuccess());
    }

    @Test
    void testEqualChildParentIdHandling() {
        var command = new CreateSkill();
        command.setSkillName("food");

        Result<SkillCreated> result = createHandler.handle(command);

        Result<SkillUpdated> result2 = changeSkill(result.getValue().getSkill(), Optional.of(result.getValue().getSkill()));

        Assertions.assertFalse(result2.isSuccess());
    }


    Result<SkillUpdated> changeSkill(ObjectId id, Optional<ObjectId> parent) {
        var command = new UpdateSkill();
        command.setSkillName("drink");
        command.setId(id.toHexString());
        if (parent.isPresent())
            command.setParentSkill(parent.get().toHexString());


        return updateHandler.handle(command);
    }
}
