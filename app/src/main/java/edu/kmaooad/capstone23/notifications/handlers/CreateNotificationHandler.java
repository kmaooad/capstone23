package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class CreateNotificationHandler implements CommandHandler<CreateNotification, NotificationCreated> {

    @Inject
    private NotificationService notificationsService;
    @Inject
    private UserService userService;

    @Override
    public Result<NotificationCreated> handle(CreateNotification command) {
        if(userService.isUserPresent(command.getUserId())) {
            return new Result<>(ErrorCode.ENTITY_NOT_FOUND, "User doesn't exist");
        }
        Notification notification = new Notification();
        notification.userId = new ObjectId(command.getUserId());
        notification.eventType = command.getEventType();
        notification.notificationMethod = command.getNotificationMethod();
        notificationsService.insert(notification);
        return new Result<>(new NotificationCreated(notification.id.toString(),command.getUserId(),command.getEventType(),command.getNotificationMethod()));
    }
}
