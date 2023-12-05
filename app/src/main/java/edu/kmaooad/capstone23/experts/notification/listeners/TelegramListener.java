package edu.kmaooad.capstone23.experts.notification.listeners;

import edu.kmaooad.capstone23.experts.notification.EventListener;
import edu.kmaooad.capstone23.experts.notification.model.Event;
import edu.kmaooad.capstone23.experts.notification.model.TelegramEvent;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class TelegramListener implements EventListener<TelegramEvent> {

    @Override
    public void send(TelegramEvent event) {
        // send telegram Event
    }
}
