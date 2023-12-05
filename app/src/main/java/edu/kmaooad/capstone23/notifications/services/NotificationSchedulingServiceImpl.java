package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import edu.kmaooad.capstone23.notifications.dal.NotificationTriggerType;
import edu.kmaooad.capstone23.notifications.dal.ScheduledNotification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class NotificationSchedulingServiceImpl implements NotificationSchedulingService {

    @Inject
    private NotificationRepository repository;

    @Override
    public ScheduledNotification insert(ScheduledNotification notification) {
        return repository.insert(notification);
    }

    @Override
    public Optional<ScheduledNotification> getNotificationByMemberAndTrigger(String memberId, NotificationTriggerType triggerType) {
        return repository.getNotificationByMemberAndTrigger(memberId, triggerType);
    }
}
