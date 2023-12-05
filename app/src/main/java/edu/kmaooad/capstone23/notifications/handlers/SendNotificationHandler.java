package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.SendNotification;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NotificationSent;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class SendNotificationHandler implements CommandHandler<SendNotification, NotificationSent> {
    @Inject
    NotificationService notificationService;
    @Inject
    UserRepository userRepository;

    @Override
    public Result<NotificationSent> handle(SendNotification command) {
        if (userRepository.findById(command.getUserId()).isEmpty()) {
            return new Result<>(ErrorCode.NOT_FOUND, "Could not find the user with id: " + command.getUserId());
        }
        Notification notification = new Notification();
        notification.notificationType = command.getNotificationType();
        notification.userId = new ObjectId(command.getUserId());
        notification.event = command.getNotificationEvent();
        notificationService.insert(notification);
        return new Result<>(new NotificationSent(notification.id.toString()));
    }
}
