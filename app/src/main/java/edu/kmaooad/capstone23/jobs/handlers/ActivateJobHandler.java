package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.ActivateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.JobActivated;
import edu.kmaooad.capstone23.jobs.service.JobService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class ActivateJobHandler implements CommandHandler<ActivateJob, JobActivated> {

    @Inject
    private JobService jobService;
    @Override
    public Result<JobActivated> handle(ActivateJob activateJobCommand) {

        Optional<Job> job = jobService.findJobById(activateJobCommand.getJobId());
        if (job.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job does not exist");
        }

        Job j = job.get();
        j.active = true;
        jobService.update(j);

        return new Result<>(new JobActivated(activateJobCommand.getJobId()));
    }
}
