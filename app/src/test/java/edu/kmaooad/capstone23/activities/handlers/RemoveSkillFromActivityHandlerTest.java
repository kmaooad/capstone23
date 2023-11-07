package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.RemoveSkillFromActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.events.SkillFromActivityRemoved;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.MongoSkillsRepository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


@QuarkusTest
public class RemoveSkillFromActivityHandlerTest {

    @Inject
    CommandHandler<RemoveSkillFromActivity, SkillFromActivityRemoved> removeSkillFromActivityHandler;

    @Inject
    private ExtracurricularActivityRepository activityRepository;
    @Inject
    private MongoSkillsRepository skillsRepository;

    private ObjectId activityId;
    private ObjectId skillId;

    @BeforeEach
    void setUp() {
        Skill skill = new Skill();
        skill.name = "Sociable";
        skillsRepository.insert(skill);
        skillId = skill.id;

        ExtracurricularActivity activity = new ExtracurricularActivity();
        activity.extracurricularActivityName = "SoftSkills";
        activity.skillIds = new ArrayList<>();
        activity.skillIds.add(skillId);
        activityRepository.insert(activity);
        activityId = activity.id;
    }

    @Test
    void testBasicSkillFromActivityRemovingHandling() {
        var command = new RemoveSkillFromActivity();
        command.setSkillId(skillId);
        command.setActivityId(activityId);

        Result<SkillFromActivityRemoved> result = removeSkillFromActivityHandler.handle(command);

        Assertions.assertEquals(result.getValue().getActivity().id,activityId);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

}
