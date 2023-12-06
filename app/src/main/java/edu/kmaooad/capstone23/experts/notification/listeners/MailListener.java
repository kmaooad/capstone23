package edu.kmaooad.capstone23.experts.notification.listeners;

import edu.kmaooad.capstone23.experts.notification.EventListener;
import edu.kmaooad.capstone23.experts.notification.model.MailEvent;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class MailListener implements EventListener<MailEvent> {

    @Override
    public void send(MailEvent event) {
        // mail send logic
    }
}
