package edu.kmaooad.capstone23.notifications.events;

public class NotificationCreated {
    private String id;
    private String userId;
    private String type;
    private String method;

    public NotificationCreated(String id, String userId, String type, String method) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.method = method;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
