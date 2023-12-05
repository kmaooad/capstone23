package edu.kmaooad.capstone23.notifications.handler;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.notifications.service.NotificationService;
import jakarta.inject.Inject;

public class CreateNotificationHandler implements CommandHandler<CreateNotification, NotificationCreated> {
    @Inject
    private NotificationService notificationsService;
    @Override
    public Result<NotificationCreated> handle(CreateNotification createNotification) {
        Notification notification = new Notification();
        notification.eventType = createNotification.getEventType();
        notification.notificationMethod = createNotification.getNotificationMethod();
        notificationsService.insert(notification);
        NotificationCreated result = new NotificationCreated(notification.notificationId);

        return new Result<NotificationCreated>(result);
    }
}
