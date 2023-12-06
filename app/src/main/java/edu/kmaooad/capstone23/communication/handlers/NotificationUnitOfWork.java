package edu.kmaooad.capstone23.communication.handlers;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class NotificationUnitOfWork implements UnitOfWork {

    @Override
    public void configure() {
        // Implement configuration logic (e.g., set up notification providers)
    }

    @Override
    public void notify(EventType eventType, String message) {
        switch (eventType) {
            case SMS:
                // Implement SMS notification logic
                break;
            case TELEGRAM:
                // Implement Telegram notification logic
                break;
            case EMAIL:
                // Implement email notification logic
                break;
            // Add more cases for other event types as needed
        }
    }
}
