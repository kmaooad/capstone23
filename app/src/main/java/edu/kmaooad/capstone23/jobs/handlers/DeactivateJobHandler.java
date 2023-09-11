package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.DeactivateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobsRepository;
import edu.kmaooad.capstone23.jobs.events.JobDeactivated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeactivateJobHandler implements CommandHandler<DeactivateJob, JobDeactivated> {
    @Inject
    private JobsRepository repository;

	@Override
	public Result<JobDeactivated> handle(DeactivateJob command) {
        Job jobToDeactivate = repository.findById(new ObjectId(command.getJobId()));

        if (jobToDeactivate == null) {
            return new Result<JobDeactivated>(ErrorCode.EXCEPTION, "Job not found");
        }

        jobToDeactivate.isActive = false;

        repository.update(jobToDeactivate);
        JobDeactivated result = new JobDeactivated(jobToDeactivate.id.toString());

        return new Result<JobDeactivated>(result);
	}
}
