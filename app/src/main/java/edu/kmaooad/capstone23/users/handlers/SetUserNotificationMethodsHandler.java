package edu.kmaooad.capstone23.users.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.users.commands.SetUserNotificationMethods;
import edu.kmaooad.capstone23.users.events.UserNotificationMethodsSetted;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SetUserNotificationMethodsHandler implements CommandHandler<SetUserNotificationMethods, UserNotificationMethodsSetted> {
    @Inject
    UserService userService;

    @Override
    public Result<UserNotificationMethodsSetted> handle(SetUserNotificationMethods command) {
        String userId = userService.setUserNotificationMethods(command.getUserId(), command.getNotificationMethods());
        UserNotificationMethodsSetted result = new UserNotificationMethodsSetted();
        result.setUserId(userId);
        return new Result<>(result);
    }

}
