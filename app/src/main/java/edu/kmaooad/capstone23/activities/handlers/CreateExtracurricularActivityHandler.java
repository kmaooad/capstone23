package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.services.ExtracurricularActivityService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.activities.commands.CreateExtracurricularActivity;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateExtracurricularActivityHandler implements CommandHandler<CreateExtracurricularActivity, ExtracurricularActivityCreated> {


    @Inject
    private ExtracurricularActivityService extracurricularActivityService;

    public Result<ExtracurricularActivityCreated> handle(CreateExtracurricularActivity command) {

        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();
        extracurricularActivity.extracurricularActivityName = command.getExtracurricularActivityName();

        extracurricularActivityService.insert(extracurricularActivity);

        ExtracurricularActivityCreated result = new ExtracurricularActivityCreated(extracurricularActivity.id.toString());

        return new Result<>(result);
    }
}
