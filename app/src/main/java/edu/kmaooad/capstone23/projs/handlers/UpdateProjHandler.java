package edu.kmaooad.capstone23.projs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.projs.commands.UpdateProj;
import edu.kmaooad.capstone23.projs.dal.ProjsRepository;
import edu.kmaooad.capstone23.projs.events.ProjUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateProjHandler implements CommandHandler<UpdateProj, ProjUpdated> {
    @SuppressWarnings("unused")
    @Inject
    ProjsRepository repository; //intentionally left non-private: https://stackoverflow.com/questions/55101095/why-does-quarkus-warn-me-about-injection-in-private-fields

    @Override
    public Result<ProjUpdated> handle(UpdateProj command) {
        @SuppressWarnings("unused")
        var projId = command.getId();
        return new Result<>(ErrorCode.EXCEPTION, "Updated");
    }
}
