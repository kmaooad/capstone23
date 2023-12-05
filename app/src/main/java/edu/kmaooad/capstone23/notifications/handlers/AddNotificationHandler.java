package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.mail.service.Notification;
import edu.kmaooad.capstone23.notifications.commands.AddNotification;
import edu.kmaooad.capstone23.notifications.commands.NotificationAdded;
import edu.kmaooad.capstone23.notifications.services.NotificationSchedulingService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.inject.Inject;

import java.util.Date;

public class AddNotificationHandler implements CommandHandler<AddNotification, NotificationAdded> {
    @Inject
    private UserRepository userRepository;
    @Inject
    private NotificationSchedulingService notificationsService;

    @Override
    public Result<NotificationAdded> handle(AddNotification command) {
        if (userRepository.findById(command.getUserId()).isEmpty())
            return new Result<>(ErrorCode.NOT_FOUND, "USER DOES NOT EXIST");
        Notification notification = new Notification(command.getNotificationType(), command.getUserId());

        notificationsService.schedule(notification, new Date());
        return new Result<>(new NotificationAdded(notification.getBody(), notification.getEmail(),
                notification.getSubject(), null));
    }
}