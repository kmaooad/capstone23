package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateProj;
import edu.kmaooad.capstone23.competences.events.ProjUpdated;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
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
