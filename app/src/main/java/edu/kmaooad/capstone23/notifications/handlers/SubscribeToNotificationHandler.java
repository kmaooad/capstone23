package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.NotificationMethod;
import edu.kmaooad.capstone23.notifications.NotificationObserver;
import edu.kmaooad.capstone23.notifications.NotificationSubject;
import edu.kmaooad.capstone23.notifications.commands.SubscribeToNotification;
import edu.kmaooad.capstone23.notifications.events.SubscribedToNotification;
import edu.kmaooad.capstone23.notifications.observers.EmailNotificationSender;
import edu.kmaooad.capstone23.notifications.observers.SMSNotificationSender;
import edu.kmaooad.capstone23.notifications.observers.TelegramNotificationSender;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SubscribeToNotificationHandler implements CommandHandler<SubscribeToNotification, SubscribedToNotification> {
    @Inject
    NotificationSubject notificationSubject;

    @Inject
    EmailNotificationSender emailNotificationSender;

    @Inject
    TelegramNotificationSender telegramNotificationSender;

    @Inject
    SMSNotificationSender smsNotificationSender;

    @Override
    public Result<SubscribedToNotification> handle(SubscribeToNotification command) {
        NotificationMethod method = command.method();
        NotificationObserver observer = toObserver(method);

        if (notificationSubject.isAttached(observer)) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Already subscribed to the notification");
        }

        notificationSubject.attach(observer);
        return new Result<>(new SubscribedToNotification(method));
    }

    public NotificationObserver toObserver(NotificationMethod method) {
        return switch (method) {
            case EMAIL -> emailNotificationSender;
            case TELEGRAM -> telegramNotificationSender;
            case SMS -> smsNotificationSender;
        };
    }
}
