package edu.kmaooad.capstone23.experts.notification.model;

import edu.kmaooad.capstone23.experts.notification.EventType;

public class Event {
    private EventType eventType;

    public Event(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }
}
