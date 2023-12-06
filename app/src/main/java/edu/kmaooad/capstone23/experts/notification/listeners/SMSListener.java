package edu.kmaooad.capstone23.experts.notification.listeners;

import edu.kmaooad.capstone23.experts.notification.EventListener;
import edu.kmaooad.capstone23.experts.notification.model.SMSEvent;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class SMSListener implements EventListener<SMSEvent> {

    @Override
    public void send(SMSEvent event) {
        // sms sending logic
    }
}
