package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.DeactivateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.jobs.events.JobDeactivated;
import edu.kmaooad.capstone23.notifications.dal.EventType;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationMethod;
import edu.kmaooad.capstone23.notifications.service.NotificationService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class DeactivateJobHandler implements CommandHandler<DeactivateJob, JobDeactivated> {

    @Inject
    private JobRepository repository;
    @Inject
    private NotificationService notificationService;
    @Override
    public Result<JobDeactivated> handle(DeactivateJob deactivateJobCommand) {

        Optional<Job> job = repository.findByIdOptional(deactivateJobCommand.getJobId());
        if(job.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "This job does not exist");

        Job j = job.get();
        j.active = false;
        repository.update(j);

        Notification notification = new Notification();
        notification.notificationMethod = NotificationMethod.EMAIL;
        notification.eventType = EventType.DEACTIVATE_JOB;
        notificationService.insert(notification);

        notificationService.sendNotifications(notification,"Job "+ job.get().id + " successfully deactivated ");

        return new Result<>(new JobDeactivated(deactivateJobCommand.getJobId()));
    }

}
