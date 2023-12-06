package edu.kmaooad.capstone23.events;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This class is used as entry point for all events in the system
// Any part of program can be observing any types of events
// Thus we are not limited to notification services only
@ApplicationScoped
public class EventManager {
    Map<EventType, List<EventListener>> listeners = new HashMap<>();

    public EventManager() {
        for (EventType type : EventType.values()) {
            this.listeners.put(type, new ArrayList<>());
        }
    }

    public void subscribe(EventType eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(EventType eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(SystemEvent event) {
        List<EventListener> users = listeners.get(event.getType());
        for (EventListener listener : users) {
            listener.onEvent(event);
        }
    }
}