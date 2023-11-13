package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.RemoveSkillFromActivity;
import edu.kmaooad.capstone23.activities.services.ExtracurricularActivityService;
import edu.kmaooad.capstone23.activities.events.SkillFromActivityRemoved;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.dal.MongoSkillsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;



@RequestScoped
public class RemoveSkillsFromActivitiesHandler implements CommandHandler<RemoveSkillFromActivity, SkillFromActivityRemoved> {

    @Inject
    private ExtracurricularActivityService activityservice;

    @Inject
    private MongoSkillsRepository skillsRepository;


    @Override
    public Result<SkillFromActivityRemoved> handle(RemoveSkillFromActivity command) {

        var skill = skillsRepository.findById(command.getSkillId().toString());
        var activity = activityservice.findByIdOptional(command.getActivityId().toString());
        

        var activityValue = activity;
        activityValue.skillIds.remove(command.getSkillId());
        activityservice.update(activityValue);

        SkillFromActivityRemoved result = new SkillFromActivityRemoved(activityValue);

        return new Result<SkillFromActivityRemoved>(result);
    }
}