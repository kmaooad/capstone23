package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.AddNotification;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationsRepository;
import edu.kmaooad.capstone23.notifications.events.NotificationAdded;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class AddNotificationHandler implements CommandHandler<AddNotification, NotificationAdded> {
    @Inject
    private UserRepository userRepository;
    @Inject
    private NotificationService notificationsService;
    @Override
    public Result<NotificationAdded> handle(AddNotification command) {
        if(userRepository.findById(command.getUserId()).isEmpty())
            return new Result<>(ErrorCode.NOT_FOUND, "USER DOES NOT EXIST");
        Notification notification = new Notification();
        notification.notificationType = command.getType();
        notification.userId = new ObjectId(command.getUserId());
        notification.sendingMethod = command.getSendingMethod();
        notificationsService.insert(notification);
        return new Result<>(new NotificationAdded(notification.id.toString(),command.getUserId(),command.getType(),command.getSendingMethod()));
    }
}
