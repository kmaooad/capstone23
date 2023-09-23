package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.activities.commands.UpdateExtraActivity;
import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.ExtraActivityUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class UpdateExtraActivityHandler implements CommandHandler<UpdateExtraActivity, ExtraActivityUpdated> {

    @Inject
    ActivityRepository cvRepository;

    @Override
    public Result<ExtraActivityUpdated> handle(UpdateExtraActivity command) {
        ExtraActivity cv = cvRepository.findById(command.getId());

        if (command.getName() != null)
            cv.name = command.getName();



        cvRepository.update(cv);

        ExtraActivityUpdated result = new ExtraActivityUpdated(cv.getId());
        return new Result<>(result);
    }
}