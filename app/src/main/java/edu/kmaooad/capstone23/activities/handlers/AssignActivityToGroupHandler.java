package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.AssignActivityToStudent;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.AssignActivityToGroupEvent;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AssignActivityToGroupHandler implements CommandHandler<AssignActivityToGroupEvent, AssignActivityToGroupEvent> {
    @Inject
    private ActivityRepository activityRepository;
    @Inject
    private GroupTemplatesRepository groupTemplatesRepository;

    @Override
    public Result<AssignActivityToGroupEvent> handle(AssignActivityToGroupEvent command) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }
    
}
