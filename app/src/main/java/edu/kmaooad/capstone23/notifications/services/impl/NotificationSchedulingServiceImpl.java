package edu.kmaooad.capstone23.notifications.services.impl;

import edu.kmaooad.capstone23.notifications.dal.NotificationOnWhatEventProceed;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import edu.kmaooad.capstone23.notifications.dal.SetupedNotification;
import edu.kmaooad.capstone23.notifications.services.NotificationSchedulingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class NotificationSchedulingServiceImpl implements NotificationSchedulingService {

    @Inject
    private NotificationRepository repository;

    @Override
    public SetupedNotification insert(SetupedNotification notification) {
        return repository.insert(notification);
    }

    @Override
    public Optional<SetupedNotification> getNotificationByMemberAndTrigger(String memberId, NotificationOnWhatEventProceed notificationOnWhatEventProceed) {
        return repository.getNotificationByMemberAndTrigger(memberId, notificationOnWhatEventProceed);
    }
}
