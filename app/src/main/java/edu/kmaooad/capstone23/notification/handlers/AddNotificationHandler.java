package edu.kmaooad.capstone23.notification.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notification.commands.AddNotificationCommand;
import edu.kmaooad.capstone23.notification.dal.Notification;
import edu.kmaooad.capstone23.notification.events.NotificationAdded;
import edu.kmaooad.capstone23.notification.services.NotificationService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class AddNotificationHandler implements CommandHandler<AddNotificationCommand, NotificationAdded> {
    @Inject
    private UserRepository userRepository;
    @Inject
    private NotificationService notificationsService;
    @Override
    public Result<NotificationAdded> handle(AddNotificationCommand command) {
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