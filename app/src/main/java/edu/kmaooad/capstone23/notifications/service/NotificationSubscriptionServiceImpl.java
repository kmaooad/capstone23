package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class NotificationSubscriptionServiceImpl implements NotificationSubscriptionService {

    @Inject
    private NotificationsRepository repository;

    @Override
    public Notification insert(Notification notification) {
        return repository.insert(notification);
    }

    @Override
    public Optional<Notification> findNotificationBy(String userId, String subscriptionMethod) {
        return Optional.ofNullable(repository.findNotificationBy(userId, subscriptionMethod));
    }
}
