package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.UnassignActivityFromGroup;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.UnassignActivityFromGroupEvent;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UnassignActivityFromGroupHandler implements CommandHandler<UnassignActivityFromGroup, UnassignActivityFromGroupEvent> {
    @Inject
    private ActivityRepository activityRepository;
    @Inject
    private GroupTemplatesRepository groupTemplatesRepository;

    @Override
    public Result<UnassignActivityFromGroupEvent> handle(UnassignActivityFromGroup command) {
        Activity activity = activityRepository.findById(command.getActivityId());
        GroupTemplate group = groupTemplatesRepository.findById(command.getGroupId());
        group.assignActivity(activity);
        group.unassignActivity(activity);
        var res = new UnassignActivityFromGroupEvent(group.id, activity.id);
        return new Result<>(res);
    }    
}
