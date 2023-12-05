package edu.kmaooad.capstone23.notifications.commands;

import edu.kmaooad.capstone23.notifications.dal.NotificationEvent;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;

public class SendNotification {
    private String UID;
    private NotifType nType;
    private NotifEvent nEvent;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public NType getNotifType() {
        return nType;
    }

    public NEvent getNEvent() {
        return nEvent;
    }

    public void setNType(NType nType) {
        this.nType = nType;
    }

    public void setNEvent(NEvent nEvent) {
        this.nEvent = nEvent;
    }
}