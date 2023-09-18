package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.CheckActivityCompletion;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.ActivityCompletionChecked;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CheckActivityCompletionHandler implements CommandHandler<CheckActivityCompletion, ActivityCompletionChecked> {

    @Inject
    private ActivityRepository repository;

    @Scheduled(every = "10s")
    public Result<ActivityCompletionChecked> handle(CheckActivityCompletion command) {

        Activity activity = new Activity(command.getName(), command.getStartDate(), command.getFinishDate());



        ActivityCompletionChecked result = new ActivityCompletionChecked(activity.id.toString(), activity.inProgress, activity.completed);

        return new Result<ActivityCompletionChecked>(result);
    }
}