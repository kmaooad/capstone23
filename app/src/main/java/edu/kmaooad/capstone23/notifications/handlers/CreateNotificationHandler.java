package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class CreateNotificationHandler
        implements CommandHandler<CreateNotification, NotificationCreated> {
    @Inject
    private UserRepository userRepository;
    @Inject
    private NotificationService notificationsService;

    @Override
    public Result<NotificationCreated> handle(CreateNotification command) {
        if(userRepository.findById(command.getUserId()).isEmpty())
            return new Result<>(ErrorCode.NOT_FOUND, "User couldn't be found");

        Notification notification = new Notification();
        notification.setType(command.getType());
        notification.setUserId(new ObjectId(command.getUserId()));
        notification.setMethod(command.getMethod());

        notificationsService.save(notification);
        return new Result<>(new NotificationCreated(notification.getId().toString(),
                command.getUserId(), command.getType(), command.getMethod()));
    }
}
