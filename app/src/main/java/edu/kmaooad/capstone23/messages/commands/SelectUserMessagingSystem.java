package edu.kmaooad.capstone23.messages.commands;

import jakarta.validation.constraints.NotNull;

public class SelectUserMessagingSystem {
    @NotNull
    String userId;

    @NotNull
    String systemType;

    @NotNull
    String systemInfo;

    @NotNull
    boolean create;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(String systemInfo) {
        this.systemInfo = systemInfo;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public static final String SYSTEM_TELEGRAM = "SYSTEM_TELEGRAM";
    public static final String SYSTEM_EMAIL = "SYSTEM_EMAIL";

}
