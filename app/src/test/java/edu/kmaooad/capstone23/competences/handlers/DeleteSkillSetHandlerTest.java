package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.events.SkillSetDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteSkillSetHandlerTest {

    @Inject
    CommandHandler<DeleteSkillSet, SkillSetDeleted> deleteHandler;

    @Inject
    private SkillSetRepository repository;

    private ObjectId idToDelete;

    @BeforeEach
    void setUp() {
        SkillSet skillSet = new SkillSet();
        skillSet.name = "SoftSkills";
        repository.insert(skillSet);
        idToDelete = skillSet.id;
    }

    @Test
    void testSuccessfulHandling() {
        var command = new DeleteSkillSet();
        command.setId(idToDelete);

        Result<SkillSetDeleted> result = deleteHandler.handle(command);

        Assertions.assertNull(repository.findById(idToDelete));
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getSkill());
    }

    @Test
    void testDeletingNonExistingSkillSet() {
        var command = new DeleteSkillSet();
        command.setId(new ObjectId());

        Result<SkillSetDeleted> result = deleteHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

}
