package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.PushNotification;
import edu.kmaooad.capstone23.notifications.events.NotificationPushed;

public class PushNotificationHandler implements CommandHandler<PushNotification, NotificationPushed> {

    @Override
    public Result<NotificationPushed> handle(PushNotification command) {
        return null;
    }
}
