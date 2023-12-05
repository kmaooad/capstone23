package edu.kmaooad.capstone23.notifications.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class NotificationTrigger {
    @NotEmpty
    private String userId;

    @NotEmpty
    private String methodInfo;

    @NotEmpty
    @Pattern(regexp = "FEEDBACK_CREATED|FEEDBACK_DELETED|FEEDBACK_UPDATED")
    private String type;

    @NotEmpty
    @Pattern(regexp = "EMAIL|TELEGRAM|VIBER")
    private String method;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMethodInfo() {
        return methodInfo;
    }

    public void setMethodInfo(String methodInfo) {
        this.methodInfo = methodInfo;
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

    public static final String EMAIL = "EMAIL";
    public static final String TELEGRAM = "TELEGRAM";
    public static final String VIBER = "VIBER";

    public static final String FEEDBACK_CREATED = "FEEDBACK_CREATED";
    public static final String FEEDBACK_DELETED = "FEEDBACK_DELETED";
    public static final String FEEDBACK_UPDATED = "FEEDBACK_UPDATED";
}
