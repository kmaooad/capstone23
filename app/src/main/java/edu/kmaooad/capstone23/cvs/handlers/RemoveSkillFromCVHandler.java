package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.RemoveSkillFromCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import edu.kmaooad.capstone23.cvs.services.CVService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped
public class RemoveSkillFromCVHandler implements CommandHandler<RemoveSkillFromCV, CVUpdated>  {

    @Inject
    CVService cvService;

    @Override
    public Result<CVUpdated> handle(RemoveSkillFromCV command) {
        if (command == null || command.getSkillId() == null || command.getCvId() == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Illegal command sent");
        }
        CV cv = cvService.findById(command.getCvId());

        if (cv == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This cv does not exist");
        }

        cv.skills.remove(command.getSkillId());
        cvService.update(cv);

        CVUpdated result = new CVUpdated(cv.id);
        return new Result<>(result);
    }
}
