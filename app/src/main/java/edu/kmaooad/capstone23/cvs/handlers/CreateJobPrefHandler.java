package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.CreateJobPref;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import edu.kmaooad.capstone23.cvs.events.JobPrefCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateJobPrefHandler implements CommandHandler<CreateJobPref, JobPrefCreated> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<JobPrefCreated> handle(CreateJobPref command) {
        JobPreference pref = new JobPreference();
        pref.industry = command.getIndustry();
        pref.location = command.getLocation();
        pref.category = command.getCategory();

        CV cv = cvRepository.findById(command.getCvId());
        cv.preference = pref;
        cvRepository.update(cv);

        JobPrefCreated result = new JobPrefCreated(cv.id);
        return new Result<>(result);
    }

}
