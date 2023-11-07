package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.AddSkillToSkillSet;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.dal.MongoSkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillToSkillSetAdded;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@QuarkusTest
public class AddSkillToSkillSetHandlerTest {

    @Inject
    CommandHandler<AddSkillToSkillSet, SkillToSkillSetAdded> addSkillToSkillSetHandler;

    @Inject
    private SkillSetRepository skillSetRepository;
    @Inject
    private MongoSkillsRepository skillsRepository;

    private ObjectId skillSetId;
    private ObjectId skillId;

    @BeforeEach
    void setUp() {
        SkillSet skillSet = new SkillSet();
        skillSet.name = "SoftSkills";
        skillSetRepository.insert(skillSet);
        skillSetId = skillSet.id;

        Skill skill = new Skill();
        skill.name = "Sociable";
        skillsRepository.insert(skill);
        skillId = skill.id;
    }

    @Test
    void testBasicSkillToSkillSetAdditionHandling() {
        var command = new AddSkillToSkillSet();
        command.setSkillId(skillId);
        command.setSkillSetId(skillSetId);

        Result<SkillToSkillSetAdded> result = addSkillToSkillSetHandler.handle(command);

        Assertions.assertEquals(result.getValue().getSkillSet().id,skillSetId);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

    @Test
    void testBasicNotValidSkillToSkillSetAdditionHandling() {
        var command = new AddSkillToSkillSet();
        command.setSkillId(new ObjectId());
        command.setSkillSetId(skillSetId);

        Result<SkillToSkillSetAdded> result = addSkillToSkillSetHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    void testNotValidSkillSetInSkillToSkillSetAdditionHandling() {
        var command = new AddSkillToSkillSet();
        command.setSkillId(skillId);
        command.setSkillSetId(new ObjectId());

        Result<SkillToSkillSetAdded> result = addSkillToSkillSetHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }


    @Test
    void testSkillAlreadyAddedToSkillSetInSkillToSkillSetAdditionHandling() {
        var command = new AddSkillToSkillSet();
        command.setSkillId(skillId);
        command.setSkillSetId(skillSetId);

        Result<SkillToSkillSetAdded> result = addSkillToSkillSetHandler.handle(command);
        Assertions.assertTrue(result.isSuccess());

        Result<SkillToSkillSetAdded> resultSecond = addSkillToSkillSetHandler.handle(command);
        Assertions.assertFalse(resultSecond.isSuccess());
    }
}
