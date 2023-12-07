package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Named("notificationHandler")
public class NotificationHandler implements CommandHandler<CreateNotification, NotificationCreated> {

    @Inject
    NotificationRepository notificationRepository;

    @Inject
    UserService userService;

    @Override
    public Result<NotificationCreated> handle(CreateNotification command) {
        if (!userService.isUserPresent(command.getRecipientId())) {
            return new Result<>(ErrorCode.NOT_FOUND, "User was not found");
        }
        List<Notification> notifications = notificationRepository.findByUserIdAndType(command.getRecipientId(), NotificationType.fromString(command.getDeliveryType()));
        Optional<Notification> optNotification = notifications.stream().filter(notification -> notification.command.equals(command.getActionCommand())).findFirst();
        if (optNotification.isPresent()) {
            return new Result<>(ErrorCode.CONFLICT, "Conflict -> Notification already exists");
        }
        Notification notification = new Notification();
        notification.userId = (command.getRecipientId());
        notification.type = (NotificationType.fromString(command.getDeliveryType()));
        notification.command = (command.getActionCommand());
        notificationRepository.insert(notification);
        return new Result<>(new NotificationCreated(notification.id.toString()));
    }
}