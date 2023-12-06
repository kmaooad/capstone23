package edu.kmaooad.capstone23.notifications.dal;

import edu.kmaooad.capstone23.notifications.dto.Event;
import edu.kmaooad.capstone23.notifications.dto.NotificationType;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

@ApplicationScoped
public class EventRepoImpl implements EventRepo {
    private static final Map<String, Set<NotificationType>> pretendDatabase = new HashMap<>();

    @Override
    public boolean addEvent(Event event) {
        if (pretendDatabase.containsKey(event.getName()))
            return false;
        else
            pretendDatabase.put(event.getName(), new HashSet<>());
        return true;
    }

    @Override
    public boolean addNotificationType(String name, NotificationType notificationType) {
        if (!pretendDatabase.containsKey(name))
            return false;
        else
            pretendDatabase.get(name).add(notificationType);
        return true;
    }

    @Override
    public Optional<Set<NotificationType>> getNotificationTypes(String name) {
        if (!pretendDatabase.containsKey(name))
            return Optional.empty();
        else
            return Optional.of(new HashSet<>(pretendDatabase.get(name)));
    }
}
