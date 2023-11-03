package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.AddSkillToCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import edu.kmaooad.capstone23.cvs.services.CVService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AddSkillToCVHandler implements CommandHandler<AddSkillToCV, CVUpdated> {

    @Inject
    CVService cvService;

    @Override
    public Result<CVUpdated> handle(AddSkillToCV command) {
        CV cv = cvService.findById(command.getCvId());

        if (cv == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "This cv does not exist");
        }

        cv.addSkill(command.getSkillId());
        cvService.update(cv);

        CVUpdated result = new CVUpdated(cv.id);
        return new Result<>(result);
    }
}
