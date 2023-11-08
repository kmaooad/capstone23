package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.RemoveSkillFromSkillSet;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.dal.MongoSkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillFromSkillSetRemoved;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


@QuarkusTest
public class RemoveSkillFromSkillSetHandlerTest {

    @Inject
    CommandHandler<RemoveSkillFromSkillSet, SkillFromSkillSetRemoved> removeSkillFromSkillSetHandler;

    @Inject
    private SkillSetRepository skillSetRepository;
    @Inject
    private MongoSkillsRepository skillsRepository;

    private ObjectId skillSetId;
    private ObjectId skillId;

    @BeforeEach
    void setUp() {
        Skill skill = new Skill();
        skill.name = "Sociable";
        skillsRepository.insert(skill);
        skillId = skill.id;

        SkillSet skillSet = new SkillSet();
        skillSet.name = "SoftSkills";
        skillSet.skillIds = new ArrayList<>();
        skillSet.skillIds.add(skillId);
        skillSetRepository.insert(skillSet);
        skillSetId = skillSet.id;
    }

    @Test
    void testBasicSkillFromSkillSetRemovingHandling() {
        var command = new RemoveSkillFromSkillSet();
        command.setSkillId(skillId);
        command.setSkillSetId(skillSetId);

        Result<SkillFromSkillSetRemoved> result = removeSkillFromSkillSetHandler.handle(command);

        Assertions.assertEquals(result.getValue().getSkillSet().id,skillSetId);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

    @Test
    void testBasicNotValidSkillFromSkillSetRemovingHandling() {
        var command = new RemoveSkillFromSkillSet();
        command.setSkillId(new ObjectId());
        command.setSkillSetId(skillSetId);

        Result<SkillFromSkillSetRemoved> result = removeSkillFromSkillSetHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    void testNotValidSkillSetInSkillFromSkillSetRemovingHandling() {
        var command = new RemoveSkillFromSkillSet();
        command.setSkillId(skillId);
        command.setSkillSetId(new ObjectId());

        Result<SkillFromSkillSetRemoved> result = removeSkillFromSkillSetHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
    @Test
    void testSkillSetDoesNotContainSkillInInSkillFromSkillSetRemovingHandling() {
        var command = new RemoveSkillFromSkillSet();
        command.setSkillId(skillId);
        command.setSkillSetId(skillSetId);

        Result<SkillFromSkillSetRemoved> result = removeSkillFromSkillSetHandler.handle(command);
        Assertions.assertTrue(result.isSuccess());

        Result<SkillFromSkillSetRemoved> resultSecond = removeSkillFromSkillSetHandler.handle(command);
        Assertions.assertFalse(resultSecond.isSuccess());

    }
}
