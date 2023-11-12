package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.DeleteExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityDeleted;
import edu.kmaooad.capstone23.activities.services.ExtracurricularCourseService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteExtracurricularActivityHandler implements CommandHandler<DeleteExtracurricularActivity, ExtracurricularActivityDeleted> {

    @Inject
    private ExtracurricularCourseService extracurricularCourseService;

    public Result<ExtracurricularActivityDeleted> handle(DeleteExtracurricularActivity command) {
        String id = command.getId();
        ExtracurricularActivity extracurricularActivity = extracurricularCourseService.findById(id);

        if (extracurricularActivity == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Extracurricular Activity not found");
        }

        extracurricularCourseService.deleteExtracurricularActivity(extracurricularActivity);

        return new Result<>(new ExtracurricularActivityDeleted(extracurricularActivity.id.toString()));
    }
}
