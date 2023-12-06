package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.notifications.commands.SubscribeNotifications;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NotificationSubscribed;
import edu.kmaooad.capstone23.notifications.service.NotificationSubscriptionService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class NotificationSubscribeHandler implements CommandHandler<SubscribeNotifications, NotificationSubscribed> {

    @Inject
    private NotificationSubscriptionService subscriptionService;
    @Inject
    private MembersRepository membersRepository;

    @Override
    public Result<NotificationSubscribed> handle(SubscribeNotifications command) {
        if(membersRepository.findById(command.getUserId()) == null){
            return new Result<>(ErrorCode.ENTITY_NOT_FOUND, "No user found");
        }
        Notification notification = new Notification();
        notification.userId = new ObjectId(command.getUserId());
        notification.notificationStatus = command.getNotificationStatus();
        notification.subscriptionMethod = command.getSubscriptionMethod();

        subscriptionService.insert(notification);

        return new Result<>(new NotificationSubscribed(
                notification.userId.toString(),
                notification.notificationStatus.toString(),
                notification.subscriptionMethod.toString()
        ));
    }
}
