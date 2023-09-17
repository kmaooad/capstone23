package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.*;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class ShowCVHandler implements CommandHandler<ShowCV, CVShowed> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVShowed> handle(ShowCV command) {
        CV cv = cvRepository.findById(command.getCvId());


        cv.visibility =true;

        cvRepository.update(cv);

        CVShowed result = new CVShowed(cv.id);
        return new Result<>(result);
    }
}
