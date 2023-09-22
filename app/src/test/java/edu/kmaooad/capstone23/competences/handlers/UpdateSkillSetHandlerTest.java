package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateSkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillSetUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@QuarkusTest
public class UpdateSkillSetHandlerTest {
    @BeforeAll
    static void deleteAllData() {
        SkillsRepository skillsRepository = new SkillsRepository();
        skillsRepository.deleteAll();
    }

    @Inject
    CommandHandler<UpdateSkillSet, SkillSetUpdated> handler;
    @Inject
    SkillSetRepository repository;
    private ObjectId idToUpdate;

    @BeforeEach
    void setUp() {
        SkillSet skillSet = new SkillSet();
        skillSet.name = "SoftSkills";
        repository.insert(skillSet);
        idToUpdate = skillSet.id;
    }


    @Test
    void testSuccessfulHandling() {
        UpdateSkillSet command = new UpdateSkillSet();
        command.setSkillSetId(idToUpdate.toString());
        command.setSkillSetName("HardSkills");

        Result<SkillSetUpdated> result = handler.handle(command);


        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getSkillSetId().isEmpty());
        Assertions.assertEquals("HardSkills", result.getValue().getSkillSetName());

        Optional<SkillSet> updatedSkilleSetOptional = repository.findById(idToUpdate.toString());
        Assertions.assertTrue(updatedSkilleSetOptional.isPresent());

        SkillSet skillSet = updatedSkilleSetOptional.get();
        Assertions.assertEquals("HardSkills", skillSet.name);
    }

    @Test
    void testNonExistentSkillSet() {
        UpdateSkillSet command = new UpdateSkillSet();
        command.setSkillSetId("abc");
        command.setSkillSetName("HardSkills");

        Result<SkillSetUpdated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
        Assertions.assertEquals(String.format("Skill set with id: %s does not exist", "abc"), result.getMessage());
    }

    @Test
    void testNameValidation() {
        UpdateSkillSet command = new UpdateSkillSet();
        command.setSkillSetId(idToUpdate.toString());
        command.setSkillSetName("HardSkills_1");

        Result<SkillSetUpdated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
        Assertions.assertEquals("must match \"^[a-zA-Z0-9 ]*$\"", result.getMessage());
    }
}
