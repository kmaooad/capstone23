package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.SetJobPref;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import edu.kmaooad.capstone23.cvs.events.JobPrefSet;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SetJobPrefHandler implements CommandHandler<SetJobPref, JobPrefSet> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<JobPrefSet> handle(SetJobPref command) {
        JobPreference pref = new JobPreference();
        pref.industry = command.getIndustry();
        pref.location = command.getLocation();
        pref.category = command.getCategory();

        CV cv = cvRepository.findById(command.getCvId());
        cv.preference = pref;
        cvRepository.update(cv);

        JobPrefSet result = new JobPrefSet(cv.id.toString());
        return new Result<>(result);
    }

}
