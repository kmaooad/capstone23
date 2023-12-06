package edu.kmaooad.capstone23.notifications.services;

import java.util.Optional;
import java.util.List;
import edu.kmaooad.capstone23.notifications.dal.UserNotification;

public interface UserNotificationsService {
    public UserNotification insert(UserNotification notification);

    public UserNotification findByUserIdAndType(String userId, String notificationType);
    public Optional<UserNotification> findByUserIdAndTypeOptional(UserNotification notification);
    public List<UserNotification> getUsersByType(String notificationType);
    
    public void remove(UserNotification notification);

    public boolean isExist(UserNotification notification);
}
