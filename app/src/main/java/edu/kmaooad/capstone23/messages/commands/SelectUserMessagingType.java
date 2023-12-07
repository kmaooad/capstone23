package edu.kmaooad.capstone23.messages.commands;

import jakarta.validation.constraints.NotNull;

public class SelectUserMessagingType {
    @NotNull
    String userId;

    @NotNull
    String messageType;


    boolean create;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }


    public static final String CREATE_ORG_MESSAGE = "CREATE_ORG";
    public static final String UPDATE_ORG_MESSAGE = "UPDATE_ORG";

}
