package edu.kmaooad.capstone23.notifications.sender;

import edu.kmaooad.capstone23.notifications.dto.NotificationType;

public interface SenderPicker {
    NotificationSender pickSender(NotificationType type);
}
