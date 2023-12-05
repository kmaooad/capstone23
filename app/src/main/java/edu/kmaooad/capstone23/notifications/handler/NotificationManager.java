package edu.kmaooad.capstone23.notifications.handler;

import edu.kmaooad.capstone23.events.EventManager;
import edu.kmaooad.capstone23.events.EventType;
import edu.kmaooad.capstone23.notifications.handler.impl.EmailNotificationHandler;
import edu.kmaooad.capstone23.notifications.handler.impl.SmsNotificationHandler;
import edu.kmaooad.capstone23.notifications.handler.impl.TelegramNotificationHandler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

// This class gathers all notification handlers and maps events to their notification methods
@ApplicationScoped
public class NotificationManager {
    @Inject
    EventManager eventManager;
    @Inject
    TelegramNotificationHandler telegramNotificationHandler;

    @Inject
    EmailNotificationHandler smsNotificationHandler;

    @Inject
    SmsNotificationHandler emailNotificationHandler;

    public NotificationManager() {
        eventManager.subscribe(EventType.DEPARTMENT_CREATED, telegramNotificationHandler);
        eventManager.subscribe(EventType.DEPARTMENT_DELETED, emailNotificationHandler);
        eventManager.subscribe(EventType.DEPARTMENT_CREATED, smsNotificationHandler);
        eventManager.subscribe(EventType.DEPARTMENT_DELETED, smsNotificationHandler);
    }

}
