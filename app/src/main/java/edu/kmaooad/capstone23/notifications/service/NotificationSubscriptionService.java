package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.notifications.commands.SubscribeNotifications;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

public interface NotificationSubscriptionService {
    Notification insert(Notification notification);
    public Optional<Notification> findNotificationBy(String userId, String subscriptionMethod);
}
