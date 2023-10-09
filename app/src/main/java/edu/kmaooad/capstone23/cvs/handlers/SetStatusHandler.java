package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.SetStatus;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class SetStatusHandler implements CommandHandler<SetStatus, CVUpdated> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVUpdated> handle(SetStatus command) {
        Optional<CV> cv = cvRepository.findByIdOptional(command.getCvId());

        if (cv.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Illegal cv id");
        }

        cv.get().status = command.getStatus();
        cvRepository.update(cv.get());

        CVUpdated result = new CVUpdated(cv.get().id);
        return new Result<>(result);
    }
}
