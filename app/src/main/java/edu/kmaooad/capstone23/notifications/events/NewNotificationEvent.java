package edu.kmaooad.capstone23.notifications.events;

public class NewNotificationEvent {
    private String id;
    private String userId;
    private String eventType;
    private String notificationType;

    public NewNotificationEvent(String id, String userId, String eventType, String notificationType) {
        this.id = id;
        this.userId = userId;
        this.eventType = eventType;
        this.notificationType = notificationType;
    }


    public String getUserId() {
        return userId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getNotificationType() {
        return notificationType;
    }


    public String setUserId() {
        return userId;
    }

    public String setEventType() {
        return eventType;
    }

    public String setNotificationType() {
        return notificationType;
    }
}