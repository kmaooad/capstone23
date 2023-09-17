package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.AddSkillToCV;
import edu.kmaooad.capstone23.cvs.commands.RemoveSkillFromCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;

public class RemoveSkillFromCVHandler implements CommandHandler<RemoveSkillFromCV, CVUpdated>  {
    CVRepository cvRepository;

    @Override
    public Result<CVUpdated> handle(RemoveSkillFromCV command) {
        CV cv = cvRepository.findById(command.getCvId());

        if (cv == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "This cv does not exist");
        }

        cv.skills.remove(command.getSkillId());
        cvRepository.update(cv);

        CVUpdated result = new CVUpdated(cv.id);
        return new Result<>(result);
    }
}
