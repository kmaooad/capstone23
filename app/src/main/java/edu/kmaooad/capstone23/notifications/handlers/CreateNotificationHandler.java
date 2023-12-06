package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.AddNotification;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class CreateNotificationHandler implements CommandHandler<AddNotification, NotificationCreated> {
    @Inject
    private UserRepository userRepository;
    @Inject
    private NotificationService notificationsService;
    @Override
    public Result<NotificationCreated> handle(AddNotification command) {
        if(userRepository.findById(command.getUserId()).isEmpty())
            return new Result<>(ErrorCode.NOT_FOUND, "User not found");
        Notification notification = new Notification();
        notification.notificationType = command.getType();
        notification.userId = new ObjectId(command.getUserId());
        notification.notificationSendMethod = command.getNotificationSendMethod();
        notification.message = command.getMessage();
        notificationsService.createNotification(notification);
        return new Result<>(new NotificationCreated(notification.id.toString()));
    }
}
