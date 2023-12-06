package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.ActivateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.JobActivated;
import edu.kmaooad.capstone23.jobs.service.JobService;
import edu.kmaooad.capstone23.notifications.dal.EventType;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationMethod;
import edu.kmaooad.capstone23.notifications.service.NotificationService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class ActivateJobHandler implements CommandHandler<ActivateJob, JobActivated> {

    @Inject
    private JobService jobService;
    @Inject
    private NotificationService notificationService;
    @Override
    public Result<JobActivated> handle(ActivateJob activateJobCommand) {

        Optional<Job> job = jobService.findJobById(activateJobCommand.getJobId().toString());
        if (job.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job does not exist");
        }

        Job j = job.get();
        j.active = true;
        jobService.update(j);

        Notification notification = new Notification();
        notification.notificationMethod = NotificationMethod.EMAIL;
        notification.eventType = EventType.ACTIVATE_JOB;
        notificationService.insert(notification);

        notificationService.sendNotifications(notification,"Job "+ job.get().id + " successfully activated ");

        return new Result<>(new JobActivated(activateJobCommand.getJobId()));
    }
}
