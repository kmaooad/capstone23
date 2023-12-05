package edu.kmaooad.capstone23.notifications;

public class Notification {
    private String representation;

    public Notification(NotificationType type, String message) {
        representation = switch (type) {
            case SUCCESS_NOTIFICATION -> "Notification about successful operation:\n" + message;
            case ERROR_NOTIFICATION -> "Notification about error:\n" + message;
        };
    }

    public String getRepresentation() {
        return representation;
    }
}
