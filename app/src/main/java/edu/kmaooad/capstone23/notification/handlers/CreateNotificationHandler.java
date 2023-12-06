package edu.kmaooad.capstone23.notification.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.jobs.service.JobService;
import edu.kmaooad.capstone23.notification.commands.CreateNotification;
import edu.kmaooad.capstone23.notification.dal.Notification;
import edu.kmaooad.capstone23.notification.event.NotificationCreated;
import edu.kmaooad.capstone23.notification.services.NotificationService;
import jakarta.inject.Inject;

public class CreateNotificationHandler implements CommandHandler<CreateNotification, NotificationCreated> {
    @Inject
    private NotificationService notificationService;

    @Override
    public Result<NotificationCreated> handle(CreateNotification command) {
        Notification notification = new Notification();
        notification.notificationId = command.getNotificationId();
        notification.notificationContent = command.getNotificationContent();
        notification.sendingProgramToUse = command.getSendingProgramToUse();
        notification.notificationAbout = command.getNotificationAbout();

        notificationService.insert(notification);
        NotificationCreated result = new NotificationCreated(notification.notificationId);

        return new Result<NotificationCreated>(result);
    }
}
