package edu.kmaooad.capstone23.experts.service;

import edu.kmaooad.capstone23.experts.dal.ExpertNotification;
import edu.kmaooad.capstone23.experts.dal.ExpertNotificationRepository;
import edu.kmaooad.capstone23.experts.dal.NotificationTriggerType;
import edu.kmaooad.capstone23.experts.dal.NotificationType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@ApplicationScoped
public class ExpertNotificationServiceImpl implements ExpertNotificationService {
    @Inject
    ExpertNotificationRepository expertNotificationRepository;

    @Inject
    EmailNotificationService emailNotificationService;

    @Inject
    SmsNotificationService smsNotificationService;

    @Inject
    MessengerNotificationService messengerNotificationService;

    @Override
    public void notify(ObjectId studentId, NotificationTriggerType triggerType, NotificationType type, String message) {
        switch (type) {
            case email -> emailNotificationService.sendMessage(studentId, triggerType, message);
            case sms -> smsNotificationService.sendMessage(studentId, triggerType, message);
            case messenger -> messengerNotificationService.sendMessage(studentId, triggerType, message);
        }
        var expertNotification = new ExpertNotification();
        expertNotification.expertId = studentId;
        expertNotification.notificationType = type;
        expertNotification.notificationTriggerType = triggerType;
        expertNotification.message = message;
        expertNotificationRepository.insert(expertNotification);
    }
}
