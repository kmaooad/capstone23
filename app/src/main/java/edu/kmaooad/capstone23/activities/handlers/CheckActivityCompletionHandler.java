package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.CheckActivityCompletion;
import edu.kmaooad.capstone23.activities.commands.CreateActivity;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.ActivityCompletionChecked;
import edu.kmaooad.capstone23.activities.events.ActivityCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.ejb.Schedule;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import io.quarkus.scheduler;

@RequestScoped
public class CheckActivityCompletionHandler implements CommandHandler<CheckActivityCompletion, ActivityCompletionChecked> {

    @Override
    public Result<ActivityCompletionChecked> handle(CheckActivityCompletion command) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

    // @Inject
    // private ActivityRepository repository;


    // public Result<ActivityCompletionChecked> handle(CheckActivityCompletion command) {

    //     Activity activity = new Activity(command.getActivityName(), command.getStartDate(), command.getFinishDate());

    //     repository.insert(activity);

    //     ActivityCompletionChecked result = new ActivityCompletionChecked();

    //     return new Result<ActivityCompletionChecked>(result);
    // }
}