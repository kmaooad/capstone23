package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class AddedNotificationHandler implements CommandHandler<CreateParticipant, ParticipantCreated> {
    @Delegate
    @Inject
    private CommandHandler<CreateParticipant, ParticipantCreated> createParticipantHandler;
    @Inject
    private NotificationService notificationService;

    @Override
    public Result<ParticipantCreated> handle(CreateParticipant command) {
        Result<ParticipantCreated> result = createParticipantHandler.handle(command);

        if (result.isSuccess()) {
            Notification notification =
                    notificationService.findByUserIdAndType(command.getUserId(),"ADDED");
            if (notification != null) {
                sendNotification(notification.getMethod());
            }
        }

        return result;
    }
    private void sendNotification(String method){
        System.out.println("Notification is sent");
    }
}