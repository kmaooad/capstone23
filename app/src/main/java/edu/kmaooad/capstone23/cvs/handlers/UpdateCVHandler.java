package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.UpdateCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class UpdateCVHandler implements CommandHandler<UpdateCV, CVUpdated> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVUpdated> handle(UpdateCV command) {
        CV cv = cvRepository.findById(new ObjectId(command.getCvId()));

        if (command.getStatus() != null)
            cv.status = command.getStatus();

        if (command.getVisibility() != null)
            cv.visibility = command.getVisibility();

        if (command.getTextInfo() != null)
            cv.textInfo = command.getTextInfo();

        cvRepository.update(cv);

        CVUpdated result = new CVUpdated(cv.id.toString());
        return new Result<>(result);
    }
}
