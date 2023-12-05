package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.NotificationTrigger;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationsRepository;
import edu.kmaooad.capstone23.notifications.events.NotificationTriggered;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class NotificationTriggeredEventHandler implements CommandHandler<NotificationTrigger, NotificationTriggered> {
    @Inject
    private UserService userService;
    @Inject
    private NotificationsRepository notificationsRepository;

    @Override
    public Result<NotificationTriggered> handle(NotificationTrigger command) {
        if (userService.isUserPresent(command.getUserId())) {
            return new Result<>(ErrorCode.NOT_FOUND, "User does not exist");
        }

        Notification notification = new Notification();
        notification.userId = new ObjectId(command.getUserId());
        notification.notificationType = command.getType();
        notification.notificationMethod = command.getMethod();

        if (notificationsRepository.isExist(notification)) {
            notificationsRepository.remove(notification);
        } else {
            notificationsRepository.insert(notification);
        }
        return new Result<>(new NotificationTriggered(
                notification.id.toString(),
                notification.userId.toString(),
                notification.notificationType,
                notification.notificationMethod));
    }
}
