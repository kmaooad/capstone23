package edu.kmaooad.capstone23.notifications.decorators;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateProj;
import edu.kmaooad.capstone23.competences.events.ProjCreated;
import edu.kmaooad.capstone23.notifications.Notification;
import edu.kmaooad.capstone23.notifications.NotificationSubject;
import edu.kmaooad.capstone23.notifications.NotificationType;
import edu.kmaooad.capstone23.notifications.observers.EmailNotificationSender;
import edu.kmaooad.capstone23.notifications.observers.SMSNotificationSender;
import edu.kmaooad.capstone23.notifications.observers.TelegramNotificationSender;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class NotifyingCreateProjectHandler implements CommandHandler<CreateProj, ProjCreated> {
    @Inject
    @Delegate
    CommandHandler<CreateProj, ProjCreated> decoratee;

    @Inject
    NotificationSubject notificationSubject;

    @Inject
    public NotifyingCreateProjectHandler() {
        // There we can control the way of notification
        notificationSubject.attach(new TelegramNotificationSender());
        notificationSubject.attach(new EmailNotificationSender());
        notificationSubject.attach(new SMSNotificationSender());
    }

    @Override
    public Result<ProjCreated> handle(CreateProj command) {
        Result<ProjCreated> handlingResult = decoratee.handle(command);
        Notification notification;

        // Here we do a notification and choose its type and message
        if (handlingResult.isSuccess()) {
            var message = "Project " + command.getName() + " created!";
            notification = new Notification(NotificationType.SUCCESS_NOTIFICATION, message);
        } else {
            var message = "ErrorCode = " + handlingResult.getErrorCode() + "\nError Message: " + handlingResult.getMessage();
            notification = new Notification(NotificationType.ERROR_NOTIFICATION, message);
        }

        notificationSubject.notify(notification);
        return handlingResult;
    }
}
