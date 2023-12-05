package edu.kmaooad.capstone23.experts.notification.model;

import edu.kmaooad.capstone23.experts.notification.EventType;

public class TelegramEvent extends Event {

    private String telegramId;
    private String message;

    public TelegramEvent(EventType eventType, String telegramId, String message) {
        super(eventType);
        this.telegramId = telegramId;
        this.message = message;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public String getMessage() {
        return message;
    }
}
