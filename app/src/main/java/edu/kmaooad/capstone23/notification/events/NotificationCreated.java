package edu.kmaooad.capstone23.notification.events;
public class NotificationCreated {

    private String id;
    private String userId;
    private String notificationType;
    private String sendingMethod;

    public NotificationCreated(String id, String userId, String notificationType, String sendingMethod) {
        this.id = id;
        this.userId = userId;
        this.notificationType = notificationType;
        this.sendingMethod = sendingMethod;
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

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getSendingMethod() {
        return sendingMethod;
    }

    public void setSendingMethod(String sendingMethod) {
        this.sendingMethod = sendingMethod;
    }
}