package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.AddSkillToCV;
import edu.kmaooad.capstone23.cvs.commands.UpdateCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class AddSkillToCVHandler implements CommandHandler<AddSkillToCV, CVUpdated> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVUpdated> handle(AddSkillToCV command) {
        CV cv = cvRepository.findById(command.getCvId());

        if (cv == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "This cv does not exist");
        }

        cv.skills.add(command.getSkillId());
        cvRepository.update(cv);

        CVUpdated result = new CVUpdated(cv.id);
        return new Result<>(result);
    }
}
