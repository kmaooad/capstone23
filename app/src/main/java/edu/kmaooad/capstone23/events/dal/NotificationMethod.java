package edu.kmaooad.capstone23.events.dal;

public enum NotificationMethod {
    EMAIL, SMS, TELEGRAM;

    public static NotificationMethod fromString(String method) {
        return switch (method) {
            case "EMAIL" -> EMAIL;
            case "SMS" -> SMS;
            case "TELEGRAM" -> TELEGRAM;
            default -> throw new IllegalArgumentException("Unknown method: " + method);
        };
    }
}
