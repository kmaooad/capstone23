package edu.kmaooad.capstone23.experts.service;

import edu.kmaooad.capstone23.experts.dal.NotificationTriggerType;
import edu.kmaooad.capstone23.experts.dal.NotificationType;
import org.bson.types.ObjectId;

public interface ExpertNotificationService {
    void notify(ObjectId expertId, NotificationTriggerType triggerType, NotificationType type, String message);
}
