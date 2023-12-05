package edu.kmaooad.capstone23.notifications.events;

public class NotificationCreated {
    private String id;
    private String userId;
    private String eventType;
    private String notificationMethod;

    public NotificationCreated(String id, String userId, String eventType, String notificationMethod) {
        this.id = id;
        this.userId = userId;
        this.eventType = eventType;
        this.notificationMethod = notificationMethod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(String notificationMethod) {
        this.notificationMethod = notificationMethod;
    }
}
