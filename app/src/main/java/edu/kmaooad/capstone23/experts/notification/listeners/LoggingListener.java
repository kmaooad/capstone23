package edu.kmaooad.capstone23.experts.notification.listeners;

import edu.kmaooad.capstone23.experts.notification.EventListener;
import edu.kmaooad.capstone23.experts.notification.model.LoggingEvent;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class LoggingListener implements EventListener<LoggingEvent> {

    @Override
    public void send(LoggingEvent event) {
        // logging event logic
    }
}
