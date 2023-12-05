package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.jobs.service.JobService;
import edu.kmaooad.capstone23.notifications.commands.UserNotificationTrigger;
import edu.kmaooad.capstone23.notifications.services.NotifyService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateJobHandler implements CommandHandler<CreateJob, JobCreated> {

    @Inject
    private JobService jobService;

    @Inject
    private NotifyService notifyService;

    public Result<JobCreated> handle(CreateJob command) {

        Job job = new Job();
        job.name = command.getName();
        job.id = command.getId();
        job.active = command.isActive();
        job.activitiesId = command.getActivitiesId();
        job.competencesId = command.getCompetencesId();

        jobService.insert(job);

        JobCreated result = new JobCreated(job.id);

        notifyService.notify(UserNotificationTrigger.JOB_CREATED, "Created job: " + job.name);

        return new Result<JobCreated>(result);
    }
}
