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
public class SetPreferenceHandler implements CommandHandler<SetPreference, JobPreferenceSeted> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<JobPrefUpdated> handle(UpdateJobPref command) {
        CV cv = cvRepository.findById(command.getCvId());

        if (command.getCategory() != null)
            cv.preference.category = command.getCategory();

        if (command.getLocation() != null)
            cv.preference.location = command.getLocation();

        if (command.getIndustry() != null)
            cv.preference.industry = command.getIndustry();

        cvRepository.update(cv);

        JobPreferenceSeted result = new JobPreferenceSeted(cv.id);
        return new Result<>(result);
    }
}
