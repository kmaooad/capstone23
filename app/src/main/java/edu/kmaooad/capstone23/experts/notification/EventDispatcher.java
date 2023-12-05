package edu.kmaooad.capstone23.experts.notification;

import edu.kmaooad.capstone23.experts.notification.listeners.LoggingListener;
import edu.kmaooad.capstone23.experts.notification.listeners.MailListener;
import edu.kmaooad.capstone23.experts.notification.listeners.TelegramListener;
import edu.kmaooad.capstone23.experts.notification.model.Event;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Map;

@RequestScoped
public class EventDispatcher {

    private Map<EventType, EventListener<Event>> eventListenerMap;

    public void subscribe(EventType eventType, EventListener<Event> eventListener) {
        eventListenerMap.putIfAbsent(eventType, eventListener);
    }

    public void notify(EventType eventType, Event event) {
        if (eventListenerMap.containsKey(eventType)) {
            eventListenerMap.get(eventType).send(event);
        }
    }

}
