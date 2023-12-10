package edu.kmaooad.capstone23.mail.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class NotificationMailServiceTest {

    @Inject
    NotificationMailService notificationMailService;


    @Test
    @DisplayName("Test sendNotification() with null notifications")
    public void testSendNotificationWithNullNotification() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            notificationMailService.sendNotification(null);
        });
    }

    @Test
    @DisplayName("Test sendNotification")
    public void testSendNotification() {
        Assertions.assertDoesNotThrow(() -> {
            Notification notification = new Notification("alex@test.com", "body");
            notificationMailService.sendNotification(notification);
        });
    }
}
