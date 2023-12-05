package edu.kmaooad.capstone23.users.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.users.commands.SetUserNotificationPreferences;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.events.UserNotificationPreferencesSetted;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SetUserNotificationPreferencesHandler implements CommandHandler<SetUserNotificationPreferences, UserNotificationPreferencesSetted> {
    @Inject
    UserService userService;

    @Override
    public Result<UserNotificationPreferencesSetted> handle(SetUserNotificationPreferences command) {
        User user = userService.updateUserNotificationPreferences(command.getUserId(), command.getNotificationTypes());
        return new Result<>(new UserNotificationPreferencesSetted(user.id.toHexString()));
    }

}
