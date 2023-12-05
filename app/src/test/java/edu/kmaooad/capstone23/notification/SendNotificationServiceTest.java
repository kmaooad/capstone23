package edu.kmaooad.capstone23.notification;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import io.quarkus.test.junit.QuarkusTest;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifications.commands.AddNotification;
import edu.kmaooad.capstone23.notifications.dal.NotificationSendMethod;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.notifications.handlers.CreateNotificationHandler;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SendNotificationServiceTest {
    @Inject
    private NotificationService service;
    @Inject
    private NotificationRepository repository;

    @Test
    @DisplayName("Send notification")
    public void sendNotificationTest(){
        Notification notification = new Notification();
        notification.message = "Message";
        notification.notificationType = NotificationType.CHAT_NOTIFICATION;
        notification.notificationSendMethod = NotificationSendMethod.EMAIL;
        repository.insert(notification);
        Assertions.assertDoesNotThrow(() -> service.sendNotificationsByType(NotificationType.CHAT_NOTIFICATION));
    }
}

