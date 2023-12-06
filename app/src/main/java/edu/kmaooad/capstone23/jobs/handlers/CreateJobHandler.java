package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.jobs.service.JobService;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateJobHandler implements CommandHandler<CreateJob, JobCreated> {

    @Inject
    JobService jobService;

    public Result<JobCreated> handle(CreateJob command) {

        Job job = new Job();
        job.name = command.getName();
        job.id = command.getId();
        job.active = command.isActive();
        job.activitiesId = command.getActivitiesId();
        job.competencesId = command.getCompetencesId();

        JobCreated result = jobService.createJob(job);

        return new Result<>(result);
    }
}
