package edu.kmaooad.capstone23.activities.handlers;
import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import edu.kmaooad.capstone23.activities.commands.CreateExtraActivity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.ExtraActivityCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class CreateExtraActivityHandler implements CommandHandler<CreateExtraActivity, ExtraActivityCreated> {

    @Inject
    ActivityRepository repository;


    @Override
    public Result<ExtraActivityCreated> handle(CreateExtraActivity command) {
        ExtraActivity activity = new ExtraActivity();
        activity.name = command.getName();

        repository.insert(activity);
        var result = new ExtraActivityCreated(activity.id.toHexString());
        return new Result<>(result);
    }
}
