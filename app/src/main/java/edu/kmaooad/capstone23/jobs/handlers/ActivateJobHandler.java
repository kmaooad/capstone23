package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.ActivateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobsRepository;
import edu.kmaooad.capstone23.jobs.events.JobActivated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class ActivateJobHandler  implements CommandHandler<ActivateJob, JobActivated> {
    @Inject
    private JobsRepository repository;


	@Override
	public Result<JobActivated> handle(ActivateJob command) {
        Job jobToActivate = repository.findById(new ObjectId(command.getJobId()));

        if (jobToActivate == null) {
            return new Result<JobActivated>(ErrorCode.EXCEPTION, "Job not found");
        }

        jobToActivate.isActive = true;

        repository.update(jobToActivate);
        JobActivated result = new JobActivated(jobToActivate.id.toString());

        return new Result<JobActivated>(result);
	}
}
