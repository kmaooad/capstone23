package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobsRepository;
import edu.kmaooad.capstone23.jobs.events.JobCreated;

import jakarta.inject.Inject;


public class CreateJobHandler implements CommandHandler<CreateJob, JobCreated> {

    @Inject
    private JobsRepository repository;

    public Result<JobCreated> handle(CreateJob command) {

        Job job = new Job();
        job.name = command.getJobName();

        repository.insert(job);

        JobCreated result = new JobCreated(job.id.toString());

        return new Result<JobCreated>(result);
    }
}
