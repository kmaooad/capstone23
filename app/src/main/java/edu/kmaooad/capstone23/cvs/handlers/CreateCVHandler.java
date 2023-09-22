package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateCVHandler implements CommandHandler<CreateCV, CVCreated> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVCreated> handle(CreateCV command) {
        CV cv = new CV();
        cv.dateTimeCreated = command.getDateTimeCreated();
        cv.textInfo = command.getTextInfo();
        cv.status = command.getStatus();
        cv.visibility = command.getVisibility();
        cv.autoAddCompetences = command.isAutoAddCompetences();
        cvRepository.insert(cv);

        CVCreated result = new CVCreated(cv.id.toString());
        return new Result<>(result);
    }

}
