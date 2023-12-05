package edu.kmaooad.capstone23.experts.notification.model;

import edu.kmaooad.capstone23.experts.notification.EventType;

public class SMSEvent extends Event{

    private String phoneNumber;
    private String text;
    private String header;
    private String Locale;

    public SMSEvent(EventType eventType, String phoneNumber, String text, String header, String locale) {
        super(eventType);
        this.phoneNumber = phoneNumber;
        this.text = text;
        this.header = header;
        Locale = locale;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getText() {
        return text;
    }

    public String getHeader() {
        return header;
    }

    public String getLocale() {
        return Locale;
    }
}
