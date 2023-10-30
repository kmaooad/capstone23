package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.UpdateExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityUpdated;
import edu.kmaooad.capstone23.activities.services.ExtracurricularCourseService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateExtracurricularActivityHandler implements CommandHandler<UpdateExtracurricularActivity, ExtracurricularActivityUpdated> {

    @Inject
    private ExtracurricularCourseService extracurricularCourseService;

    public Result<ExtracurricularActivityUpdated> handle(UpdateExtracurricularActivity command) {

        ExtracurricularActivity extracurricularActivity = extracurricularCourseService.findById(command.getId());
        if (extracurricularActivity == null) {
            return new Result(ErrorCode.EXCEPTION, "Extracurricular activity not found");
        }

        extracurricularActivity.extracurricularActivityName = command.getExtracurricularActivityName();
        extracurricularActivity.extracurricularActivityDate = command.getExtracurricularActivityDate();

        extracurricularCourseService.update(extracurricularActivity);

        ExtracurricularActivityUpdated result = new ExtracurricularActivityUpdated(extracurricularActivity.id.toString(), extracurricularActivity.extracurricularActivityName, extracurricularActivity.extracurricularActivityDate);

        return new Result(result);
    }
}
