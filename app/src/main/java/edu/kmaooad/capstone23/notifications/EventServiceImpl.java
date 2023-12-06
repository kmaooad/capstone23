package edu.kmaooad.capstone23.notifications;

import edu.kmaooad.capstone23.notifications.dal.EventRepo;
import edu.kmaooad.capstone23.notifications.dto.Event;
import edu.kmaooad.capstone23.notifications.dto.NotificationType;
import edu.kmaooad.capstone23.notifications.sender.SenderPicker;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EventServiceImpl implements EventService {
    @Inject
    EventRepo eventRepo;

    @Override
    public boolean registerEventType(Event event) {
        return eventRepo.addEvent(event);
    }

    @Override
    public boolean addNotificationType(String name, NotificationType type) {
        return eventRepo.addNotificationType(name, type);
    }

    @Override
    public boolean isRegistered(String name) {
        return eventRepo.getNotificationTypes(name).isPresent();
    }

    @Override
    public boolean issueEvent(String name, SenderPicker senderPicker) {
        var types = eventRepo.getNotificationTypes(name);
        if (types.isEmpty())
            return false;
        else {
            var result = true;
            for (NotificationType type : types.get())
                result = result && senderPicker.pickSender(type).sendNotification(name, name);
            return result;
        }
    }
}
