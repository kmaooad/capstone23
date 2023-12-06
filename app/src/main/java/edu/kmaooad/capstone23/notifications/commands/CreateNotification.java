package edu.kmaooad.capstone23.notifications.commands;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateNotification {
    @NotBlank
    private String recipientId;
    @NotBlank
    @Pattern(regexp = "EMAIL|SMS|TELEGRAM")
    private String deliveryType;
    @NotBlank
    private String actionCommand;

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getActionCommand() {
        return actionCommand;
    }

    public void setActionCommand(String actionCommand) {
        this.actionCommand = actionCommand;
    }
}