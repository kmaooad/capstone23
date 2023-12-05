package edu.kmaooad.capstone23.massages.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;

import edu.kmaooad.capstone23.common.Result;

import edu.kmaooad.capstone23.massages.commands.CreateMessage;
import edu.kmaooad.capstone23.massages.dal.Message;
import edu.kmaooad.capstone23.massages.events.MessageCreated;
import edu.kmaooad.capstone23.massages.services.MessageService;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

    public class MessageCreateHandler implements CommandHandler<CreateMessage, MessageCreated> {

        @Inject
        private MessageService notificationsService;
        @Override
        public Result<MessageCreated> handle(CreateMessage command) {
           Message notification = new Message();
            notification.event_type = command.getEvent_type();
            notification.method_of_sending = command.getMethod_of_sending();
            notification.messageText= command.getMessageText();
            notificationsService.insert(notification);
            return new Result<>(new MessageCreated(notification.id));
        }
    }




