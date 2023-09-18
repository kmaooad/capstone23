package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@RequestScoped
public class CreateCVHandler implements CommandHandler<CreateCV, CVCreated> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVCreated> handle(CreateCV command) {
        CV cv = new CV();

        if (command.getDateTimeCreated() == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Creation time is not set");
        } else if (command.getDateTimeCreated().isAfter(LocalDateTime.now())) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Invalid time of creation");
        } else if (command.getTextInfo() != null && command.getTextInfo().isBlank()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Text info cannot be blank");
        }

        cv.dateTimeCreated = command.getDateTimeCreated();
        cv.textInfo = command.getTextInfo();
        cv.status = command.getStatus();
        cv.visibility = command.getVisibility();
        cv.autoAddCompetences = command.isAutoAddCompetences();

        cvRepository.insert(cv);

        CVCreated result = new CVCreated(cv.id);
        return new Result<>(result);
    }

}
