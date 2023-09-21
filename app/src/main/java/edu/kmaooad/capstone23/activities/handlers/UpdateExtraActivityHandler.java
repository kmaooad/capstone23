package edu.kmaooad.capstone23.activities.handlers;
import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import edu.kmaooad.capstone23.activities.commands.UpdateExtraActivity;

import edu.kmaooad.capstone23.activities.events.ExtraActivityUpdated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateExtraActivityHandler implements CommandHandler<UpdateExtraActivity, ExtraActivityUpdated> {


    @Inject
    ActivityRepository cvRepository;
    @Override
    public Result<ExtraActivityUpdated> handle(UpdateExtraActivity command) {
        ExtraActivity act = cvRepository.findById(new ObjectId(command.getCvId()));

        if (command.getName() != null)
            act.name = command.getName();


        var result = new ExtraActivityUpdated(activity.id.toHexString());
        return new Result<>(result);
    }
}
