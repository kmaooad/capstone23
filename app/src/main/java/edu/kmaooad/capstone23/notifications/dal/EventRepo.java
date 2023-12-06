package edu.kmaooad.capstone23.notifications.dal;

import edu.kmaooad.capstone23.notifications.dto.Event;
import edu.kmaooad.capstone23.notifications.dto.NotificationType;

import java.util.Optional;
import java.util.Set;

public interface EventRepo {
    boolean addEvent(Event event);

    boolean addNotificationType(String name, NotificationType notificationType);

    Optional<Set<NotificationType>> getNotificationTypes(String name);
}
