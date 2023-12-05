package edu.kmaooad.capstone23.experts.service;

import edu.kmaooad.capstone23.experts.dal.NotificationTriggerType;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class SmsNotificationServiceImpl implements SmsNotificationService{
    public void sendMessage(ObjectId expertId, NotificationTriggerType triggerType, String message){
        //TODO send by sms
    }
}
