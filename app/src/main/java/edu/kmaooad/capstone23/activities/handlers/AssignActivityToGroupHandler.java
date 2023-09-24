package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.AssignActivityToGroup;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.AssignActivityToGroupEvent;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AssignActivityToGroupHandler implements CommandHandler<AssignActivityToGroup, AssignActivityToGroupEvent> {
    @Inject
    private ActivityRepository activityRepository;
    @Inject
    private GroupTemplatesRepository groupTemplatesRepository;

    @Override
    public Result<AssignActivityToGroupEvent> handle(AssignActivityToGroup command) {
        Activity activity = activityRepository.findById(command.getActivityId());
        GroupTemplate group = groupTemplatesRepository.findById(command.getGroupId());
        group.assignActivity(activity);
        var res = new AssignActivityToGroupEvent(group.id, activity.id);
        return new Result<>(res);
    }
    
}
