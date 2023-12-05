package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.dal.NotificationOnWhatEventProceed;
import edu.kmaooad.capstone23.notifications.dal.SetupedNotification;
import edu.kmaooad.capstone23.notifications.services.NotificationSchedulingService;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class ProceedNotificationHandler implements CommandHandler<BanEntity, EntityBanned> {

    @Inject
    @Delegate
    private CommandHandler<BanEntity, EntityBanned> decoratee;

    @Inject
    private NotificationSchedulingService notificationService;

    @Override
    public Result<EntityBanned> handle(BanEntity command) {
        Result<EntityBanned> result = decoratee.handle(command);
        if (!result.isSuccess()) {
            return result;
        }

        processNotificationIfPresent(command);
        return result;
    }

    private void processNotificationIfPresent(BanEntity command) {
        notificationService.getNotificationByMemberAndTrigger(command.getEntityId().toString(), NotificationOnWhatEventProceed.USER_BANNED)
                .ifPresent(this::sendNotification);
    }

    private void sendNotification(SetupedNotification notification) {
        switch (notification.notificationPlaceToSend) {
            case EMAIL:
                //send notifications to email
                break;
            case SMS:
                //send notifications to sms
                break;
            case TG:
                //send notifications to telegram
                break;
        }
    }
}
