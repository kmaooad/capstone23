package edu.kmaooad.capstone23.members.services.impl;

import edu.kmaooad.capstone23.members.services.NotificationService;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.dal.NotificationTypeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {

    @Inject
    NotificationTypeRepository notificationTypeRepository;

    @Override
    public Optional<NotificationType> findByUserAndType(String userId, String notificationType) {
        return notificationTypeRepository.findByUserAndType(userId, notificationType);
    }
}
