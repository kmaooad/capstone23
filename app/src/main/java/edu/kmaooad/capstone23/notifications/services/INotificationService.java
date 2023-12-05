package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.models.Event;

public interface INotificationService {

    boolean sendNotification(String userId, Event event, String message);
}
