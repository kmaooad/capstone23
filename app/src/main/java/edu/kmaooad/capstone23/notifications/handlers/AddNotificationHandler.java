package edu.kmaooad.capstone23.notifications.handler;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.notifications.commands.AddNotification;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NotificationAdded;
import edu.kmaooad.capstone23.notifications.service.NotificationService;
import jakarta.inject.Inject;

public class AddNotificationHandler implements CommandHandler<AddNotification, NotificationAdded> {
    @Inject
    private NotificationService notificationsService;
    @Override
    public Result<NotificationAdded> handle(AddNotification AddNotification) {
        Notification notification = new Notification();
        notification.eventType = AddNotification.getEventType();
        notification.notificationMethod = AddNotification.getNotificationMethod();
        notificationsService.insert(notification);
        NotificationAdded result = new NotificationAdded(notification.id);

        return new Result<NotificationAdded>(result);
    }
}
