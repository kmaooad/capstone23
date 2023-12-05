package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.dal.PushNotification;
import edu.kmaooad.capstone23.notifications.services.PushNotificationService;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.events.UserCreated;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class UserCreatedPushNotificationHandler implements CommandHandler<CreateUser, UserCreated> {

    @Delegate
    @Inject
    CommandHandler<CreateUser, UserCreated> commandHandlerDecorateee;

    @Inject
    PushNotificationService service;

    @Override
    public Result<UserCreated> handle(CreateUser command) {
        Result<UserCreated> result = commandHandlerDecorateee.handle(command);
        if(result.isSuccess()) {
            // getEmailName() is temporary solution
            PushNotification notification = service.findNotificationBy(
                    command.getEmailName(),
                    "USER_CREATED"
            );
            if(notification != null)
                pushNotification(notification);
        }
        return result;
    }

    private void pushNotification(PushNotification notification){
        System.out.println("MERRY CHRISTMAS!");
    }
}
