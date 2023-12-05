package edu.kmaooad.capstone23.notifications.sender;

import edu.kmaooad.capstone23.notifications.dto.NotificationType;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SenderPickerImpl implements SenderPicker {
    @Override
    public NotificationSender pickSender(NotificationType type) {
        return switch (type) {
            case EMAIL -> (eventName, payload) -> {
                System.out.println("Let's pretend I sent an email!");
                return true;
            };
            case MESSENGER -> (eventName, payload) -> {
                System.out.println("Let's pretend I sent a message!");
                return true;
            };
            case SMS -> (eventName, payload) -> {
                System.out.println("Let's pretend I sent an SMS!");
                return true;
            };
        };
    }
}
