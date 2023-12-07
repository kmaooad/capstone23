package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
@Priority(100)
public class NotificationDecoratorHandler<T1, T2> implements CommandHandler<T1, T2> {

    @Inject
    @Delegate
    CommandHandler<T1, T2> delegateHandler;

    @Inject
    NotificationService notificationService;

    @Override
    public Result<T2> handle(T1 command) {
        String type = command.getClass().getSimpleName();
        notificationService.sendNotification(type);
        return delegateHandler.handle(command);
    }
}