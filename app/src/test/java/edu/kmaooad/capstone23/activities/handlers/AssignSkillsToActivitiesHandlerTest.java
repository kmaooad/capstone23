package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.AddSkillToActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.events.SkillToActivityAdded;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.AddSkillToSkillSet;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillToSkillSetAdded;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AssignSkillsToActivitiesHandlerTest {
    @Inject
    CommandHandler<AddSkillToActivity, SkillToActivityAdded> addSkillToActivityHandler;

    @Inject
    private ExtracurricularActivityRepository activityRepository;
    @Inject
    private SkillsRepository skillsRepository;

    private ObjectId activityId;
    private ObjectId skillId;

    @BeforeEach
    void setUp() {
        ExtracurricularActivity activity = new ExtracurricularActivity();
        activity.extracurricularActivityName = "Web dev hackathon";
        activityRepository.insert(activity);
        activityId = activity.id;

        Skill skill = new Skill();
        skill.name = "Http protocol";
        skillsRepository.insert(skill);
        skillId = skill.id;
    }

    @Test
    void testBasicSkillToSkillSetAdditionHandling() {
        var command = new AddSkillToActivity();
        command.setSkillId(skillId);
        command.setActivityId(activityId);

        var result = addSkillToActivityHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(result.getValue().getActivity().id, activityId);
    }

    @Test
    void testSkillAlreadyAddedToSkillSetInSkillToSkillSetAdditionHandling() {
        var command = new AddSkillToActivity();
        command.setSkillId(skillId);
        command.setActivityId(activityId);

        Result<SkillToActivityAdded> result = addSkillToActivityHandler.handle(command);
        Assertions.assertTrue(result.isSuccess());

        Result<SkillToActivityAdded> resultSecond = addSkillToActivityHandler.handle(command);
        Assertions.assertFalse(resultSecond.isSuccess());
    }
}
