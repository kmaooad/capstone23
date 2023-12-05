package edu.kmaooad.capstone23.experts.notification.model;

import edu.kmaooad.capstone23.experts.notification.EventType;

public class MailEvent extends Event {

    private String mail;
    private String message;
    private String header;

    public MailEvent(EventType eventType, String mail, String message, String header) {
        super(eventType);
        this.mail = mail;
        this.message = message;
        this.header = header;
    }

    public String getMail() {
        return mail;
    }

    public String getMessage() {
        return message;
    }

    public String getHeader() {
        return header;
    }
}
