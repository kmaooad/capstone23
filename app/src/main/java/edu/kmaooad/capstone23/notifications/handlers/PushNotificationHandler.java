package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.dal.PushNotification;
import edu.kmaooad.capstone23.notifications.commands.PushNotificationCommand;
import edu.kmaooad.capstone23.notifications.events.NotificationPushed;
import edu.kmaooad.capstone23.notifications.services.PushNotificationService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class PushNotificationHandler implements CommandHandler<PushNotificationCommand, NotificationPushed> {

    @Inject
    UserRepository userRepository;
    @Inject
    PushNotificationService notificationsService;
    @Override
    public Result<NotificationPushed> handle(PushNotificationCommand command) {
        if(userRepository.findById(command.getUserId()).isEmpty())
            return new Result<>(ErrorCode.ENTITY_NOT_FOUND, "COULD NOT FIND USER " + command.getUserId());

        PushNotification notification = new PushNotification();
        notification.notificationEvent = command.getNotificationEvent();
        notification.userId = new ObjectId(command.getUserId());
        notification.notificationDestination = command.getNotificationDestination();
        notificationsService.insert(notification);

        return new Result<>(
                new NotificationPushed(
                        notification.id.toString(),
                        command.getUserId(),
                        command.getNotificationEvent(),
                        command.getNotificationDestination()
                )
        );
    }
}
