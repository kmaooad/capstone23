package edu.kmaooad.capstone23.massages.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

    public class CreateMessage {
       private String messageText;
        @NotEmpty
        private String event_type;

        public String getMessageText() {
            return messageText;
        }

        public void setMessageText(String messageText) {
            this.messageText = messageText;
        }

        @NotEmpty
        @Pattern(regexp = "sms|email|telegram")
        private String method_of_sending;


        public CreateMessage(String messageText, String event_type, String method_of_sending) {
            this.messageText = messageText;
            this.event_type = event_type;
            this.method_of_sending = method_of_sending;
        }


        public String getEvent_type() {
            return event_type;
        }

        public void setEvent_type(String type) {
            this.event_type = type;
        }

        public String getMethod_of_sending() {
            return method_of_sending;
        }

        public void setMethod_of_sending(String method_of_sending) {
            this.method_of_sending = method_of_sending;
        }
    }

