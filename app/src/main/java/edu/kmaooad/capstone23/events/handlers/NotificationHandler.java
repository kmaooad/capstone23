package edu.kmaooad.capstone23.events.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.events.commands.CreateNotification;
import edu.kmaooad.capstone23.events.dal.Notification;
import edu.kmaooad.capstone23.events.dal.NotificationMethod;
import edu.kmaooad.capstone23.events.dal.NotificationRepository;
import edu.kmaooad.capstone23.events.events.NotificationCreated;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class NotificationHandler implements CommandHandler<CreateNotification, NotificationCreated> {

    @Inject
    NotificationRepository notificationRepository;

    @Inject
    UserService userService;

    @Override
    public Result<NotificationCreated> handle(CreateNotification command) {
        if (!userService.isUserPresent(command.getUserId())) {
            return new Result<>(ErrorCode.NOT_FOUND, "User not found");
        }

        List<Notification> notifications = notificationRepository.getByUserIdAndType(command.getUserId(), NotificationMethod.fromString(command.getMethod()));
        Optional<Notification> exist = notifications.stream().filter(notification -> notification.command.equals(command.getCommand())).findFirst();
        if (exist.isPresent()) {
            return new Result<>(ErrorCode.CONFLICT, "Notification already exists");
        }

        Notification notification = new Notification();
        notification.userId = (command.getUserId());
        notification.method = (NotificationMethod.fromString(command.getMethod()));
        notification.command = (command.getCommand());
        notificationRepository.insert(notification);

        return new Result<>(new NotificationCreated(notification.id.toString()));
    }
}
