package edu.kmaooad.capstone23.notification.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.bson.types.ObjectId;

public class CreateNotification {

    private ObjectId userId;

    @NotEmpty
    private String notificationType;

    @NotEmpty
    private String sendingMethod;

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getType() {
        return notificationType;
    }

    public void setType(String type) {
        this.notificationType = type;
    }

    public String getSendingMethod() {
        return sendingMethod;
    }

    public void setSendingMethod(String sendingMethod) {
        this.sendingMethod = sendingMethod;
    }
}
