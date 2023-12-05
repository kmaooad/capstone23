package edu.kmaooad.capstone23.notifications.handlers;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.users.services.UserService;
import edu.kmaooad.capstone23.notifications.commands.UserNotificationTrigger;
import edu.kmaooad.capstone23.notifications.dal.UserNotification;
import edu.kmaooad.capstone23.notifications.events.UserNotificationTriggered;
import edu.kmaooad.capstone23.notifications.services.UserNotificationsService;
import jakarta.inject.Inject;

public class UserNotificationTriggerHandler implements CommandHandler<UserNotificationTrigger, UserNotificationTriggered> {

    @Inject
    private UserService userService;

    @Inject
    private UserNotificationsService notificationsService;

    @Override
    public Result<UserNotificationTriggered> handle(UserNotificationTrigger command) {
        if(userService.isUserPresent(command.getUserId()))
            return new Result<>(ErrorCode.NOT_FOUND, "User doesn`t exist");

        UserNotification notification = new UserNotification();
        notification.notificationType = command.getType();
        notification.userId = new ObjectId(command.getUserId());
        notification.notificationMethod = command.getMethod();
        notification.notificationMethodInfo = command.getMethodInfo();

        if(notificationsService.isExist(notification)){
            notificationsService.remove(notification);
        }else{
            notificationsService.insert(notification);
        }
        return new Result<>(new UserNotificationTriggered(notification.id.toString(),command.getUserId(),command.getType(),command.getMethod()));
    }

}