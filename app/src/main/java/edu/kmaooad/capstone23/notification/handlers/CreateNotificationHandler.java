package edu.kmaooad.capstone23.notification.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notification.commands.CreateNotification;
import edu.kmaooad.capstone23.notification.dal.Notification;
import edu.kmaooad.capstone23.notification.events.NotificationCreated;
import edu.kmaooad.capstone23.notification.services.NotificationService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class CreateNotificationHandler implements CommandHandler<CreateNotification, NotificationCreated> {
    @Inject
    UserRepository userRepository;
    @Inject
    NotificationService notificationsService;

    @Override
    public Result<NotificationCreated> handle(CreateNotification command) {
        if (userRepository.findById(command.getUserId().toString()).isEmpty()) {
            return new Result<>(ErrorCode.NOT_FOUND, "USER DOES NOT EXIST");
        }
        Notification notification = new Notification();
        notification.notificationType = command.getType();
        notification.userId = command.getUserId();
        notification.sendingMethod = command.getSendingMethod();
        notificationsService.insert(notification);
        return new Result<>(new NotificationCreated(notification.id, command.getUserId(), command.getType(), command.getSendingMethod()));
    }
}
