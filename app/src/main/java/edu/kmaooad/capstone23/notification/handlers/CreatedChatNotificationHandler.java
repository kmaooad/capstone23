package edu.kmaooad.capstone23.notification.handlers;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.notification.dal.Notification;
import edu.kmaooad.capstone23.notification.services.NotificationService;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class CreatedChatNotificationHandler implements CommandHandler<CreateParticipant, ParticipantCreated> {

    @Delegate
    @Inject
    private CommandHandler<CreateParticipant, ParticipantCreated> commandHandlerDecorated;

    @Inject
    private NotificationService service;

    @Override
    public Result<ParticipantCreated> handle(CreateParticipant command) {
        Result<ParticipantCreated> result = commandHandlerDecorated.handle(command);
        if(result.isSuccess()){
            Notification notification = service.findNotificationBy(command.getUserId(),"ADDED_TO_CHAT");
            if(notification != null){
                sendNotification(notification.sendingMethod);
            }
        }
        return result;
    }
    private void sendNotification(String sendingMethod){}
}