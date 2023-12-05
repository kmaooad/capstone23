package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.NotificationTriggerType;
import edu.kmaooad.capstone23.notifications.dal.ScheduledNotification;

import java.util.Optional;

public interface NotificationSchedulingService {
    ScheduledNotification insert(ScheduledNotification notification);
    public Optional<ScheduledNotification> getNotificationByMemberAndTrigger(String memberId, NotificationTriggerType triggerType);
}
