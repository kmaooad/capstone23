package edu.kmaooad.capstone23.experts.service;

import edu.kmaooad.capstone23.experts.dal.NotificationTriggerType;
import org.bson.types.ObjectId;

public interface SmsNotificationService {
    void sendMessage(ObjectId expertId, NotificationTriggerType triggerType, String message);
}
