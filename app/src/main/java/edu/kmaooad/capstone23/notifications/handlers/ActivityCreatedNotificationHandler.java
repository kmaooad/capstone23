package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.activities.commands.CreateExtracurricularActivity;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.services.NotificationService;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class ActivityCreatedNotificationHandler implements CommandHandler<CreateExtracurricularActivity, ExtracurricularActivityCreated> {

    @Inject
    @Delegate
    private CommandHandler<CreateExtracurricularActivity, ExtracurricularActivityCreated> decoratee;
    @Inject
    private NotificationService notificationService;

    @Override
    public Result<ExtracurricularActivityCreated> handle(CreateExtracurricularActivity command) {
        Result<ExtracurricularActivityCreated> result = decoratee.handle(command);
        if(result.isSuccess()) {
            Notification notification = notificationService.findNotificationByIdAndType(command.getActivityId(),"ACTIVITY_CREATED");
            if(notification != null) {
                sendNotification(notification.notificationMethod);
            }
        }
        return result;
    }

    private void sendNotification(String notificationMethod) {
        if(notificationMethod.equals("EMAIL")) {
            // handle email notification sending
        } else if (notificationMethod.equals("SMS")) {
            // handle sms notification sending
        } else if (notificationMethod.equals("TELEGRAM")) {
            // handle telegram notification sending
        }
    }
}
