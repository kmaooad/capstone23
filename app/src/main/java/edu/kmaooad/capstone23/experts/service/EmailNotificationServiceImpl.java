package edu.kmaooad.capstone23.experts.service;

import edu.kmaooad.capstone23.experts.dal.NotificationTriggerType;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class EmailNotificationServiceImpl implements EmailNotificationService {
    public void sendMessage(ObjectId expertId, NotificationTriggerType triggerType, String message){
        //TODO send by email
    }
}
