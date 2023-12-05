package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.service.NotificationSubscriptionService;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

import java.util.Optional;

@Decorator
public class NotificationSubscribedChatHandler implements CommandHandler<CreateParticipant, ParticipantCreated>{

        @Delegate
        @Inject
        private CommandHandler<CreateParticipant, ParticipantCreated> commandHandlerDecoratee;

        @Inject
        private NotificationSubscriptionService service;

        @Override
        public Result<ParticipantCreated> handle(CreateParticipant command) {
            Result<ParticipantCreated> result = commandHandlerDecoratee.handle(command);
            if(result.isSuccess()){
                Optional<Notification> notification = service.findNotificationBy(command.getUserId(),"ADDED_TO_CHAT");
                if(notification.isPresent()){
                    /*
                    Proceed to send notifications regarding the status of notification subscription
                     */
                }
            }
            return result;
        }
}
