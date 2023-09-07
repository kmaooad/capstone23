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

        Result<SkillUpdated> result2 = changeSkill(result.getValue().getSkill());

        Assertions.assertTrue(result2.isSuccess());
        Assertions.assertNotNull(result2.getValue());
        Assertions.assertNotNull(result2.getValue().getSkill());
    }


    Result<SkillUpdated> changeSkill(ObjectId id) {
        var command = new UpdateSkill();
        command.setSkillName("drink");
        command.setId(id);


        Result<SkillUpdated> result = updateHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getSkill());
        return result;
    }
}
