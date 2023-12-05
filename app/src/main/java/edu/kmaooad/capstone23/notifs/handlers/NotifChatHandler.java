package edu.kmaooad.capstone23.notifs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.notifs.dal.Notif;
import edu.kmaooad.capstone23.notifs.service.NotifService;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class NotifChatHandler implements CommandHandler<CreateParticipant, ParticipantCreated> {

    @Delegate
    @Inject
    private CommandHandler<CreateParticipant, ParticipantCreated> commandHandlerDecoratee;

    @Inject
    private NotifService service;

    @Override
    public Result<ParticipantCreated> handle(CreateParticipant command) {
        Result<ParticipantCreated> result = commandHandlerDecoratee.handle(command);
        if(result.isSuccess()){
            Notif notification = service.findNotifBy(command.getUserId(),"ADDED_TO_CHAT");
            if(notification != null){
                sendNotif(notification.notificationMethod);
            }
        }
        return result;
    }

    private void sendNotif(String notificationMethod){
        //sending code should be added for working
    }
}