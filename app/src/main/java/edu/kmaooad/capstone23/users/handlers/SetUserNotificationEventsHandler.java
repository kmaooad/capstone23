package edu.kmaooad.capstone23.users.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.users.commands.SetUserNotificationEvents;
import edu.kmaooad.capstone23.users.events.UserNotificationEventsSetted;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SetUserNotificationEventsHandler implements CommandHandler<SetUserNotificationEvents, UserNotificationEventsSetted> {
    @Inject
    UserService userService;

    @Override
    public Result<UserNotificationEventsSetted> handle(SetUserNotificationEvents command) {
        String userId = userService.setUserNotificationEvents(command.getUserId(), command.getNotificationEvents());
        UserNotificationEventsSetted result = new UserNotificationEventsSetted();
        result.setUserId(userId);
        return new Result<>(result);
    }

}
