package edu.kmaooad.capstone23.notifications.services.impl;

import java.util.Optional;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationSourceEvent;
import edu.kmaooad.capstone23.notifications.dal.NotificationsRepository;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import jakarta.inject.Inject;

public class NotificationServiceImpl implements NotificationService {
@Inject
private NotificationsRepository repository;

	@Override
	public Notification insert(Notification notification) {
        return repository.insert(notification);
	}

	@Override
	public Optional<Notification> findByIdAndSource(String userId, NotificationSourceEvent source) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByIdAndSource'");
	}

}
