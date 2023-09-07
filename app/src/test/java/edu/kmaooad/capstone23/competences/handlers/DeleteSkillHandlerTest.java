package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteSkill;
import edu.kmaooad.capstone23.competences.events.SkillDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteSkillHandlerTest {

    @Inject
    CommandHandler<DeleteSkill, SkillDeleted> handler;

    @Test
    void testSuccessfulHandling() {
        var command = new DeleteSkill();
        command.setId(new ObjectId("64f83a309682b0261c7bd24e"));

        Result<SkillDeleted> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getSkill());
    }

    // @Test
    // void testSuccessfulRelationHandling() {
    //     var command = new DeleteSkill();
    //     command.setSkillName("food");

    //     Result<SkillDeleted> result = handler.handle(command);

    //     Assertions.assertNotNull(result.getValue().getSkill());

    //     var command2 = new DeleteSkill();
    //     command2.setSkillName("fruit");
    //     command2.setParentSkill(result.getValue().getSkill());

    //     Result<SkillDeleted> result2 = handler.handle(command2);

    //     Assertions.assertTrue(result2.isSuccess());
    //     Assertions.assertNotNull(result2.getValue());
    //     Assertions.assertNotNull(result2.getValue().getSkill());
    // }
}
