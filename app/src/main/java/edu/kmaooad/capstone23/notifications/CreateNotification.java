package edu.kmaooad.capstone23.notifications.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class CreateNotification {
    @NotEmpty
    private String userId;
    @NotEmpty
    @Pattern(regexp = "ADDED|DELETED")
    private String type;
    @NotEmpty
    @Pattern(regexp = "EMAIL|SMS|MESSENGER")
    private String method;

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