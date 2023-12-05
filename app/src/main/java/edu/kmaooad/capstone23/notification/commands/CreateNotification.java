package edu.kmaooad.capstone23.notification.commands;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

public class CreateNotification {

    private ObjectId notificationId;
    @NotBlank
    private String notificationContent;
    @NotBlank
    private String sendingProgramToUse;

    @NotBlank
    private String notificationAbout;

    public CreateNotification(ObjectId notificationId, String notificationAbout, String notificationContent, String sendingProgramToUse) {
        this.notificationId = notificationId;
        this.notificationContent = notificationContent;
        this.sendingProgramToUse = sendingProgramToUse;
        this.notificationAbout = notificationAbout;
    }

    public ObjectId getNotificationId() {
        return notificationId;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public String getSendingProgramToUse() {
        return sendingProgramToUse;
    }
    public String getNotificationAbout() {return notificationAbout;}

    public void setNotificationId(ObjectId notificationId) {
        this.notificationId = notificationId;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public void setSendingProgramToUse(String sendingProgramToUse) {
        this.sendingProgramToUse = sendingProgramToUse;
    }


    public void setNotificationAbout(String notificationAbout) {
        this.notificationAbout = notificationAbout;
    }

}
