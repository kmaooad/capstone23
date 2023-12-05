package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.SetNotificationType;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.dal.NotificationTypeRepository;
import edu.kmaooad.capstone23.notifications.events.NotificationTypeSet;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SetNotificationTypeHandler implements CommandHandler<SetNotificationType, NotificationTypeSet> {

    @Inject
    NotificationTypeRepository notificationTypeRepository;

    @Override
    public Result<NotificationTypeSet> handle(SetNotificationType command) {
        try {
            var notificationType = new NotificationType();
            notificationType.notificationType = command.getNotificationType();
            notificationType.notificationMethod = command.getNotificationMethod();
            notificationType.userId = command.getUserId();

            notificationTypeRepository.insert(notificationType);
            return new Result<>(new NotificationTypeSet());
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.EXCEPTION, e.toString());
        }
    }
}
