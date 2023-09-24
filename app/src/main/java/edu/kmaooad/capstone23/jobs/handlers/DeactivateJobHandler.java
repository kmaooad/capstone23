package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.DeactivateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.JobDeactivated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class DeactivateJobHandler implements CommandHandler<DeactivateJob, JobDeactivated> {

    @Inject
    private JobRepository repository;
    @Override
    public Result<JobDeactivated> handle(DeactivateJob deactivateJobCommand) {

        Optional<Job> job = repository.findByIdOptional(deactivateJobCommand.getJobId());
        if(job.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job does not exist");

        Job j = job.get();
        j.active = false;
        repository.update(j);

        return new Result<>(new JobDeactivated(deactivateJobCommand.getJobId()));
    }

}
