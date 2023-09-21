package edu.kmaooad.capstone23.activities.handlers;
import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import edu.kmaooad.capstone23.activities.commands.CreateExtraActivity;

import edu.kmaooad.capstone23.activities.events.ExtraActivityCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateExtraActivityHandler implements CommandHandler<CreateExtraActivity, ExtraActivityCreated> {


    @Override
    public Result<ExtraActivityCreated> handle(CreateExtraActivity command) {
        ExtraActivity activity = new ExtraActivity();
        activity.name = command.getName();


        var result = new ExtraActivityCreated(activity.id.toHexString());
        return new Result<>(result);
    }
}
