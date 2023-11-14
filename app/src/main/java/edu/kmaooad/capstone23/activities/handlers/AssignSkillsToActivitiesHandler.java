package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.AddSkillToActivity;
import edu.kmaooad.capstone23.activities.events.SkillToActivityAdded;
import edu.kmaooad.capstone23.activities.services.ExtracurricularActivityService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
@RequestScoped
public class AssignSkillsToActivitiesHandler implements CommandHandler<AddSkillToActivity, SkillToActivityAdded> {

    @Inject
    private ExtracurricularActivityService extracurricularActivityService;

    @Inject
    private MongoSkillsRepository skillsRepository;


    @Override
    public Result<SkillToActivityAdded> handle(AddSkillToActivity command) {

        var skill = skillsRepository.findById(command.getSkillId().toString());
        var activity = extracurricularActivityService.findById(command.getActivityId().toString());

        if (skill.isEmpty())
            return new Result<>(ErrorCode.EXCEPTION, "Has not existing skill");

        if (activity == null)
            return new Result<>(ErrorCode.EXCEPTION, "Has not existing skillSet");

        var skillIds = activity.skillIds;
        if (skillIds != null && skillIds.contains(command.getSkillId())) {
            return new Result<>(ErrorCode.EXCEPTION, "Already contains skill");
        }

        if (skillIds != null && skillIds.contains(command.getSkillId())) {
            return new Result<>(ErrorCode.EXCEPTION, "Already contains skill");
        }

        if (skillIds == null) {
            activity.skillIds = new ArrayList<>();
        }
        activity.skillIds.add(command.getSkillId());
        extracurricularActivityService.update(activity);

        SkillToActivityAdded result = new SkillToActivityAdded(activity);

        return new Result<>(result);
    }
}

