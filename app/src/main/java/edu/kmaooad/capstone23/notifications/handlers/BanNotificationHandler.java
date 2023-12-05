package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.dal.NotificationTriggerType;
import edu.kmaooad.capstone23.notifications.services.NotificationSchedulingService;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class BanNotificationHandler implements CommandHandler<BanEntity, EntityBanned> {

    @Inject
    @Delegate
    private CommandHandler<BanEntity, EntityBanned> decoratee;

    @Inject
    private NotificationSchedulingService notificationService;

    @Override
    public Result<EntityBanned> handle(BanEntity command) {
        var result = decoratee.handle(command);
        if(!result.isSuccess())
            return result;
        var notification = notificationService.getNotificationByMemberAndTrigger(command.getEntityId().toString(), NotificationTriggerType.USER_BANNED);
        if(notification.isPresent()) {
            switch (notification.get().notificationType){
                case email:
                    //send email
                    break;
                case sms:
                    //send sms
                    break;
                case telegram:
                    //send telegram message
                    break;
            }
        }
        return result;
    }
}
