package edu.kmaooad.capstone23.activities.handlers;
import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import edu.kmaooad.capstone23.activities.commands.DeleteExtraActivity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.events.ExtraActivityDeleted;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.DeleteCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import edu.kmaooad.capstone23.common.ErrorCode;
import java.util.Optional;
@RequestScoped
public class DeleteExtraActivityHandler implements CommandHandler<DeleteExtraActivity, ExtraActivityDeleted> {

    @Inject
    ActivityRepository repository;




    @Override
    public Result<ExtraActivityDeleted> handle(DeleteExtraActivity command) {
        ObjectId id = command.getId();
        ExtraActivity cv = repository.findById(id);

        if (cv == null) {
            return new Result<>(ErrorCode.EXCEPTION, "CV not found");
        }

        repository.delete(cv);

        return new Result<>(new ExtraActivityDeleted(cv.getId()));
    }
}
