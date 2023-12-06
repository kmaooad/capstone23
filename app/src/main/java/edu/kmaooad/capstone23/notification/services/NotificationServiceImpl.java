package edu.kmaooad.capstone23.notification.services;

import edu.kmaooad.capstone23.notification.dal.Notification;
import edu.kmaooad.capstone23.notification.dal.NotificationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {
    @Inject
    private NotificationRepository notificationRepo;
    @Override
    public Notification insert(Notification notification) {
        return notificationRepo.insert(notification);
    }

    @Override
    public void sending(Notification notification) {
        if(notification.sendingProgramToUse!=null && !notification.sendingProgramToUse.isEmpty()) {
            switch (notification.sendingProgramToUse){
                case "email":
                    //відправляємо notification.notificationContent у email
                    break;
                case "telegram":
                    //відправляємо notification.notificationContent у telegram сповіщення
                    break;
                case "sms":
                    //відправляємо notification.notificationContent у sms
                    break;
                default:
                    //сповіщення, що показує, що не правильно подано значення на спосіб насилання повідомлення
            }
    }
    }

}
