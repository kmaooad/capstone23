package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.NotificationOnWhatEventProceed;
import edu.kmaooad.capstone23.notifications.dal.SetupedNotification;

import java.util.Optional;

public interface NotificationSchedulingService {
    SetupedNotification insert(SetupedNotification notification);
    public Optional<SetupedNotification> getNotificationByMemberAndTrigger(String memberId, NotificationOnWhatEventProceed notificationOnWhatEventProceed);
}
