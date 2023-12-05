package edu.kmaooad.capstone23.notifications;

import edu.kmaooad.capstone23.notifications.dto.Event;
import edu.kmaooad.capstone23.notifications.dto.NotificationType;
import edu.kmaooad.capstone23.notifications.sender.SenderPicker;

public interface EventService {
    boolean registerEventType(Event event);

    boolean addNotificationType(String name, NotificationType type);

    boolean isRegistered(String name);

    boolean issueEvent(String name, SenderPicker senderPicker);
}
