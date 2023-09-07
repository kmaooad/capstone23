package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteJobHandler implements CommandHandler<CreateJob, JobCreated> {


    @Override
    public Result<JobCreated> handle(CreateJob command) {
        return null;
    }
}
