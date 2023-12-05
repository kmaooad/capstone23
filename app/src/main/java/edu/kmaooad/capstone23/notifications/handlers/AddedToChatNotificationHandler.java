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
public class AddedToChatNotificationHandler implements CommandHandler<CreateParticipant, ParticipantCreated> {

    @Delegate
    @Inject
    private CommandHandler<CreateParticipant, ParticipantCreated> commandHandlerDecoratee;

    @Inject
    private NotificationService service;

    @Override
    public Result<ParticipantCreated> handle(CreateParticipant command) {
        Result<ParticipantCreated> result = commandHandlerDecoratee.handle(command);
        if(result.isSuccess()){
            Notification notification = service.findNotificationBy(command.getUserId(),"ADDED_TO_CHAT");
            if(notification != null){
                sendNotification(notification.sendingMethod);
            }
        }
        return result;
    }

    private void sendNotification(String sendingMethod){
        //Logic to handle different sending methods
        //Maybe call another service to proceed
    }
}
