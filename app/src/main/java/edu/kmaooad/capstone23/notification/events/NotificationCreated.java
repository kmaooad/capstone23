package edu.kmaooad.capstone23.notification.events;

import org.bson.types.ObjectId;

public class NotificationCreated {
    private ObjectId id;
    private ObjectId userId;
    private String notificationType;
    private String sendingMethod;

    public NotificationCreated(ObjectId id, ObjectId userId, String notificationType, String sendingMethod) {
        this.id = id;
        this.userId = userId;
        this.notificationType = notificationType;
        this.sendingMethod = sendingMethod;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
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
