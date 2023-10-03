package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.CheckActivityCompletion;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.events.ActivityCompletionChecked;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CheckActivityCompletionHandler implements CommandHandler<CheckActivityCompletion, ActivityCompletionChecked> {


    public Result<ActivityCompletionChecked> handle(CheckActivityCompletion command) {
        Activity activity = command.getActivity();

        activity.inProgress = command.getActualDate().before(activity.startDate) ? false : 
                                command.getActualDate().after(activity.finishDate) ? false : true;
        activity.completed = command.getActualDate().after(activity.finishDate) ? true : false;

        ActivityCompletionChecked result = new ActivityCompletionChecked(activity.inProgress, activity.completed);

        return new Result<ActivityCompletionChecked>(result);
    }
}