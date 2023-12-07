package edu.kmaooad.capstone23.notifications;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@Decorator
public class NotificationSendingHandler<T1, T2> implements CommandHandler<T1, T2> {

    @Inject
    @Delegate
    private CommandHandler<T1, T2> decoratee;

    @Inject
    Instance<NotificationChannel> logStrategies;

    @Override
    public Result<T2> handle(T1 command) {
        Result<T2> result = decoratee.handle(command);
        if (result.isSuccess()) {
            NotificationChannel notificationChannel = getNotificationChannel((Class<T2>) result.getValue().getClass());
            if (notificationChannel != null) {
                notificationChannel.sendNotification("Command " + command.getClass().getSimpleName() + " executed successfully");
            }
        }
        return result;
    }

    private NotificationChannel getNotificationChannel(Class<T2> commandClass) {
        var annotation = commandClass.getAnnotation(Notify.class);
        if (annotation == null) {
            return null;
        }

        Class<? extends NotificationChannel> notificationChannelClass = annotation.value();

        return logStrategies.select(notificationChannelClass).get();
    }
}
