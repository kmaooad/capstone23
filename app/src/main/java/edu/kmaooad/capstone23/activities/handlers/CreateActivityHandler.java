package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.CreateActivity;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.ActivityCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateActivityHandler implements CommandHandler<CreateActivity, ActivityCreated> {

    @Inject
    private ActivityRepository repository;

    public Result<ActivityCreated> handle(CreateActivity command) {

        Activity activity = new Activity(command.getActivityName());

        repository.insert(activity);

        ActivityCreated result = new ActivityCreated(activity.id.toString());

        return new Result<ActivityCreated>(result);
    }
}