//package edu.kmaooad.capstone23.activities.handlers;
//import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
//import edu.kmaooad.capstone23.activities.commands.DeleteExtraActivity;
//import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
//import edu.kmaooad.capstone23.activities.events.ExtraActivityDeleted;
//import edu.kmaooad.capstone23.common.CommandHandler;
//import edu.kmaooad.capstone23.common.Result;
//import jakarta.enterprise.context.RequestScoped;
//import jakarta.inject.Inject;
//import org.bson.types.ObjectId;
//import edu.kmaooad.capstone23.common.ErrorCode;
//import java.util.Optional;
//@RequestScoped
//public class DeleteExtraActivityHandler implements CommandHandler<DeleteExtraActivity, ExtraActivityDeleted> {
//
//    @Inject
//    ActivityRepository repository;
//
//
//    @Override
//    public Result<ExtraActivityDeleted> handle(DeleteExtraActivity command) {
//        Optional<ExtraActivity> course = repository.findById(command.getId());
//        if (course.isEmpty())
//            return new Result<>(ErrorCode.VALIDATION_FAILED, "Activity with this ID does not exist");
//
//
//
//        repository.delete(course.get());
//        var result = new ExtraActivityDeleted(activity.getId());
//        return new Result<>(result);
//    }
//}
