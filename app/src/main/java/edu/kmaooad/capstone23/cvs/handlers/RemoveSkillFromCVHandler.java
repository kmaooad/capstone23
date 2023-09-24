package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.AddSkillToCV;
import edu.kmaooad.capstone23.cvs.commands.RemoveSkillFromCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class RemoveSkillFromCVHandler implements CommandHandler<RemoveSkillFromCV, CVUpdated>  {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVUpdated> handle(RemoveSkillFromCV command) {
        if (command == null || command.getSkillId() == null || command.getCvId() == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Illegal command sent");
        }
        Optional<CV> cv = cvRepository.findByIdOptional(command.getCvId());

        if (cv.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This cv does not exist");
        }

        cv.get().skills.remove(command.getSkillId());
        cvRepository.update(cv.get());

        CVUpdated result = new CVUpdated(cv.get().id);
        return new Result<>(result);
    }
}
