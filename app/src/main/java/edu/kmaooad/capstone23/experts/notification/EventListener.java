package edu.kmaooad.capstone23.experts.notification;

import edu.kmaooad.capstone23.experts.notification.model.Event;

public interface EventListener<E extends Event> {

    public abstract void send(E event);

}
