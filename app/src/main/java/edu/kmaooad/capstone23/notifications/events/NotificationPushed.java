package edu.kmaooad.capstone23.notifications.events;

public class NotificationPushed {
    private String id;
    private String userId;
    private String notificationEvent;
    private String notificationDestination;

    public NotificationPushed(
            String id,
            String userId,
            String notificationEvent,
            String notificationDestination
    ) {
        this.id = id;
        this.userId = userId;
        this.notificationEvent = notificationEvent;
        this.notificationDestination = notificationDestination;
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

    public String getNotificationEvent() {
        return notificationEvent;
    }

    public void setNotificationEvent(String notificationEvent) {
        this.notificationEvent = notificationEvent;
    }

    public String getNotificationDestination() {
        return notificationDestination;
    }

    public void setNotificationDestination(String notificationDestination) {
        this.notificationDestination = notificationDestination;
    }
}
