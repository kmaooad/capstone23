package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.service.NotificationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AddToChatHandler implements CommandHandler<CreateParticipant, ParticipantCreated> {

    @Inject
    private CommandHandler<CreateParticipant, ParticipantCreated> commandHandlerDecoratee;
    @Inject
    private NotificationService service;

    public Result<ParticipantCreated> handle(CreateParticipant command) {
        Result<ParticipantCreated> result = commandHandlerDecoratee.handle(command);
        if(result.isSuccess()){
            Notification notification = service.findNotificationBy(command.getUserId(),"ADDED_TO_CHAT");
            if(notification != null){
                service.sendNotification(notification.notificationMethod);
            }
        }
        return result;
    }

    private void sendNotification(Notification.notificationMethod notificationMethod){
        break;
    }
}
