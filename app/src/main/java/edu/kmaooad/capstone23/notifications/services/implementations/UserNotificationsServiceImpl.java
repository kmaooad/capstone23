package edu.kmaooad.capstone23.notifications.services.implementations;

import java.util.List;
import java.util.Optional;

import edu.kmaooad.capstone23.notifications.dal.UserNotification;
import edu.kmaooad.capstone23.notifications.dal.UserNotificationsRepository;
import edu.kmaooad.capstone23.notifications.services.UserNotificationsService;
import jakarta.inject.Inject;

public class UserNotificationsServiceImpl implements UserNotificationsService{
    
    @Inject
    private UserNotificationsRepository notificationsRepository;

    @Override
    public UserNotification insert(UserNotification notification){
        return notificationsRepository.insert(notification);
    }

    @Override
    public UserNotification findByUserIdAndType(String userId, String notificationType){
        return notificationsRepository.findByUserIdAndType(userId, notificationType);
    }

    @Override
    public Optional<UserNotification> findByUserIdAndTypeOptional(UserNotification notification){
        return notificationsRepository.findByUserIdAndTypeOptional(notification);
    }
    
    @Override
    public void remove(UserNotification notification){
        notificationsRepository.remove(notification);
    }

    @Override
    public boolean isExist(UserNotification notification){
        return notificationsRepository.isExist(notification);
    }

    @Override
    public List<UserNotification> getUsersByType(String notificationType){
        return notificationsRepository.getUsersByType(notificationType);
    }
}
