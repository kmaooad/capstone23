package edu.kmaooad.capstone23.experts.notification;

import edu.kmaooad.capstone23.experts.notification.listeners.LoggingListener;
import edu.kmaooad.capstone23.experts.notification.listeners.MailListener;
import edu.kmaooad.capstone23.experts.notification.listeners.SMSListener;
import edu.kmaooad.capstone23.experts.notification.listeners.TelegramListener;
import edu.kmaooad.capstone23.experts.notification.model.Event;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Map;

@RequestScoped
public class EventDispatcher {

    protected Map<EventType, EventListener> eventListenerMap;

    @Inject
    public EventDispatcher(LoggingListener loggingListener, TelegramListener telegramListener,
                                                 MailListener mailListener, SMSListener smsListener) {
        subscribe(EventType.LOGGING, loggingListener);
        subscribe(EventType.TELEGRAM, telegramListener);
        subscribe(EventType.SMS, smsListener);
        subscribe(EventType.MAIL, mailListener);
    }

    public void subscribe(EventType eventType, EventListener eventListener) {
        eventListenerMap.putIfAbsent(eventType, eventListener);
    }

    public void notify(EventType eventType, Event event) {
        if (eventListenerMap.containsKey(eventType)) {
            eventListenerMap.get(eventType).send(event);
        }
    }

}
