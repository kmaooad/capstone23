package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.activities.commands.UpdateExtraActivity;
import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.ExtraActivityUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class UpdateExtraActivityHandler implements CommandHandler<UpdateExtraActivity, ExtraActivityUpdated> {

    @Inject
    ActivityRepository cvRepository;

    @Override
    public Result<ExtraActivityUpdated> handle(UpdateExtraActivity command) {
        Optional<ExtraActivity> extraActivity = cvRepository.findByIdOptional(command.getId());

        if (extraActivity.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Activity with this ID does not exist");

        if (command.getName() != null)
            extraActivity.get().name = command.getName();



        cvRepository.update(extraActivity.get());

        ExtraActivityUpdated result = new ExtraActivityUpdated(extraActivity.get().id);
        return new Result<>(result);
    }
}