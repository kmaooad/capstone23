package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.ShowCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CV.Visibility;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ShowCVHandler implements CommandHandler<ShowCV, CVUpdated> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVUpdated> handle(ShowCV command) {
        CV cv = cvRepository.findById(command.getCvId());


        cv.visibility =Visibility.VISIBLE;

        cvRepository.update(cv);

        CVUpdated result = new CVUpdated(cv.id);
        return new Result<>(result);
    }
}
