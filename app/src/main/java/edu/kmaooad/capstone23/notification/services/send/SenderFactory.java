package edu.kmaooad.capstone23.notification.services.send;

import edu.kmaooad.capstone23.notification.dal.NotificationDestination;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SenderFactory {

    @Inject
    private EmailNotificationSender emailSender;

    @Inject
    private TelegramNotificationSender tgSender;

    @Inject
    private SMSNotificationSender smsSender;

    public NotificationSender getSender(NotificationDestination destination) {
        return switch (destination) {
            case EMAIL -> emailSender;
            case SMS -> smsSender;
            case TELEGRAM -> tgSender;
        };
    }
}
