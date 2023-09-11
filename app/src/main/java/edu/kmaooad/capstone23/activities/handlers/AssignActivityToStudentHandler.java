package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.AssignActivityToStudent;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.AssignActivityToGroup;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AssignActivityToStudentHandler implements CommandHandler<AssignActivityToStudent, AssignActivityToGroup> {
    @Inject
    private ActivityRepository activityRepository;

    @Override
    public Result<AssignActivityToGroup> handle(AssignActivityToStudent command) {
        Activity activity = activityRepository.findById(command.getActivityId());
        
        return
    }
    
}
