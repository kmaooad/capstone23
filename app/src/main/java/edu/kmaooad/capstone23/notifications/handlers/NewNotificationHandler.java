package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.NewNotificationCommand;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NewNotificationEvent;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class NewNotificationHandler implements CommandHandler<NewNotificationCommand, NewNotificationEvent> {

    @Inject
    private NotificationService notificationService;

    @Override
    public Result<NewNotificationEvent> handle(NewNotificationCommand command) {
        Notification notification = new Notification();
        notification.userId = new ObjectId(command.getUserId());
        notification.eventType = command.getEventType();
        notification.notificationType = command.getNotificationType();
        notification = notificationService.createNotification(notification);
        return new Result<>(new NewNotificationEvent(notification.id.toString(), command.getUserId(), command.getEventType(), command.getNotificationType()));
    }
}
