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
public class SetStatusHandler implements CommandHandler<SetStatus, StatusSeted> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<StatusSeted> handle(SetStatus command) {
        CV cv = cvRepository.findById(command.getCvId());


        if (command.getStatus() != null)
            cv.status = command.getStatus();

        cvRepository.update(cv);

        StatusSeted result = new StatusSeted(cv.id);
        return new Result<>(result);
    }
}
