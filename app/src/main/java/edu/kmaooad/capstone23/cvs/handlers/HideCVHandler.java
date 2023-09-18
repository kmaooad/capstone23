package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.HideCV;
import edu.kmaooad.capstone23.cvs.dal.CV;

import edu.kmaooad.capstone23.cvs.dal.CV.Visibility;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class HideCVHandler implements CommandHandler<HideCV, CVUpdated> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVUpdated> handle(HideCV command) {
        CV cv = cvRepository.findById(command.getCvId());


        cv.visibility =Visibility.HIDDEN;

        cvRepository.update(cv);

        CVUpdated result = new CVUpdated(cv.id);
        return new Result<>(result);
    }
}
