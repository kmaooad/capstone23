package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.commands.DeleteSkill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.competences.events.SkillDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteSkillHandlerTest {

    @Inject
    CommandHandler<CreateSkill, SkillCreated> createHandler;

    @Inject
    CommandHandler<DeleteSkill, SkillDeleted> deleteHandler;

    @Inject
    private SkillsRepository repository;

    ObjectId createTestSkill() {
        var command = new CreateSkill();
        command.setSkillName("creep walk");

        Result<SkillCreated> result = createHandler.handle(command);

        return result.getValue().getSkill();
    }

    @Test
    void testSuccessfulHandling() {
        var skillToDelete = createTestSkill();

        Assertions.assertNotNull(repository.findById(skillToDelete));

        var command = new DeleteSkill();
        command.setId(skillToDelete);

        Result<SkillDeleted> result = deleteHandler.handle(command);

        Assertions.assertNull(repository.findById(skillToDelete));

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getSkill());
    }

    @Test
    void testDeletingNonExistingSkill() {
        var command = new DeleteSkill();
        command.setId(new ObjectId());

        Result<SkillDeleted> result = deleteHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    void testDeletingSkillWithChildren() {
        var skillToDelete = createTestSkill();

        var command = new CreateSkill();
        command.setSkillName("hustlin");
        command.setParentSkill(skillToDelete);

        Result<SkillCreated> result = createHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getSkill());

        var command2 = new DeleteSkill();
        command2.setId(skillToDelete);

        Result<SkillDeleted> result2 = deleteHandler.handle(command2);

        Assertions.assertFalse(result2.isSuccess());
    }
}
