package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.UpdateJobPref;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.services.CVService;
import edu.kmaooad.capstone23.cvs.events.JobPrefUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateJobPrefHandler implements CommandHandler<UpdateJobPref, JobPrefUpdated> {

    @Inject
    CVService cvService;

    @Override
    public Result<JobPrefUpdated> handle(UpdateJobPref command) {
        CV cv = cvService.findById(command.getCvId());

        if (command.getCategory() != null)
            cv.preference.category = command.getCategory();

        if (command.getLocation() != null)
            cv.preference.location = command.getLocation();

        if (command.getIndustry() != null)
            cv.preference.industry = command.getIndustry();

        cvService.update(cv);

        JobPrefUpdated result = new JobPrefUpdated(cv.id);
        return new Result<>(result);
    }
}
