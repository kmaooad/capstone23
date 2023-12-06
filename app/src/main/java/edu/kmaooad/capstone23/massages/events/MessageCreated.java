package edu.kmaooad.capstone23.massages.events;

import org.bson.types.ObjectId;


    public class MessageCreated {

        private ObjectId messageId;

        public ObjectId getMessageId(){
            return messageId;
        }

        public MessageCreated(ObjectId messageIdId) {
            this.messageId = messageIdId;
        }
    }

