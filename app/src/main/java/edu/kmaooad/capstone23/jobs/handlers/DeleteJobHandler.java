package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.DeleteJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.JobDeleted;
import edu.kmaooad.capstone23.jobs.service.JobService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class DeleteJobHandler implements CommandHandler<DeleteJob, JobDeleted> {

    @Inject
    private JobService jobService;
    @Override
    public Result<JobDeleted> handle(DeleteJob commandDel) {

        Optional<Job> job = jobService.findJobById(commandDel.getJobId().toString());
        if(job.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job was previously deleted or never existed");

        Job j = job.get();

        jobService.delete(j);

        return new Result<>(new JobDeleted(commandDel.getJobId()));
    }
}
