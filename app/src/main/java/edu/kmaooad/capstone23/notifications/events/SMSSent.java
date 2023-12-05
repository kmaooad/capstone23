package edu.kmaooad.capstone23.notifications.events;

public record SMSSent(String recipient, long timeSent) implements NotificationSent {}
