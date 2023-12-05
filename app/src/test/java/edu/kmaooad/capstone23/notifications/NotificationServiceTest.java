package edu.kmaooad.capstone23.notifications;

import edu.kmaooad.capstone23.notifications.dto.Event;
import edu.kmaooad.capstone23.notifications.dto.NotificationType;
import edu.kmaooad.capstone23.notifications.sender.SenderPicker;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
public class NotificationServiceTest {
    @Inject
    EventService eventService;

    @Inject
    SenderPicker senderPicker;

    // UoW # 1
    @Test
    void testRegisteringAnEvent() {
        var newEvent = new Event(UUID.randomUUID().toString(), "event");
        eventService.registerEventType(newEvent);
        Assertions.assertTrue(eventService.isRegistered(newEvent.getName()));
    }

    @Test
    void testAddingNotificationTypeToEvent() {
        var newEvent = new Event(UUID.randomUUID().toString(), "event");
        eventService.registerEventType(newEvent);
        Assertions.assertTrue(eventService.addNotificationType(newEvent.getName(), NotificationType.EMAIL));
    }

    // UoW # 2
    @Test
    void testIssuingAnEvent() {
        var newEvent = new Event(UUID.randomUUID().toString(), "event");
        eventService.registerEventType(newEvent);
        eventService.addNotificationType(newEvent.getName(), NotificationType.EMAIL);
        Assertions.assertTrue(eventService.issueEvent(newEvent.getName(), senderPicker));
    }
}
