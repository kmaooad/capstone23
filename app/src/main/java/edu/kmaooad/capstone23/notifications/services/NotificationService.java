package edu.kmaooad.capstone23.notifications.services;

import java.util.Optional;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationSourceEvent;

public interface NotificationService {
	public Notification insert(Notification notification);
    public Optional<Notification> findByIdAndSource(String userId, NotificationSourceEvent source);
}
