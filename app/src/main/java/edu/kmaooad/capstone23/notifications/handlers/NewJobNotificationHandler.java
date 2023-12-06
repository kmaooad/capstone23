package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.notifications.services.NotificationSchedulingService;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

public class NewJobNotificationHandler implements CommandHandler<CreateJob, JobCreated> {

    @Inject
    @Delegate
    private CommandHandler<CreateJob, JobCreated> handler;

    @Inject
    private NotificationSchedulingService notificationsService;

    @Override
    public Result<JobCreated> handle(CreateJob command) {
        var result = handler.handle(command);

        var notification = notificationsService.findNotificationBy(
                command.getName(), "");

        if (notification == null) {
            // handle...
        }

        return result;
    }
}
