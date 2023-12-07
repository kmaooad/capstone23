package edu.kmaooad.capstone23.members.services;

import edu.kmaooad.capstone23.notifications.dal.NotificationType;

import java.util.Optional;

public interface NotificationService {
    Optional<NotificationType> findByUserAndType(String userId, String notificationType);
}
