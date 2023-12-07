package edu.kmaooad.capstone23.notifications.dal;

public enum NotificationType {
    EMAIL, SMS, TELEGRAM;

    public static NotificationType fromString(String type) {
        switch (type) {
            case "EMAIL":
                return EMAIL;
            case "SMS":
                return SMS;
            case "TELEGRAM":
                return TELEGRAM;
            default:
                throw new IllegalArgumentException("Invalid method: " + type);
        }
    }
}