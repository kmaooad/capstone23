package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.NewNotificationCommand;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NewNotificationEvent;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@QuarkusTest
@ExtendWith(MockitoExtension.class)
public class NewNotificationHandlerTests {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NewNotificationHandler newNotificationHandler;

    @Test
    @DisplayName("Handle New Notification Command")
    public void handleNewNotificationCommandTest() {
        // Arrange
        String userId = new ObjectId().toString();
        String eventType = "LOGIN_SUCCESS";
        String notificationMethod = "EMAIL";
        NewNotificationCommand command = new NewNotificationCommand(userId, eventType, notificationMethod);

        Notification notification = new Notification();
        notification.userId = new ObjectId(userId);
        notification.eventType = eventType;
        notification.notificationType = notificationMethod;

        Mockito.when(notificationService.createNotification(Mockito.any())).thenReturn(notification);

        // Act
        Result<NewNotificationEvent> result = newNotificationHandler.handle(command);

        // Assert
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        NewNotificationEvent event = result.getValue();
        Assertions.assertEquals(userId, event.getUserId());
        Assertions.assertEquals(eventType, event.getEventType());
        Assertions.assertEquals(notificationMethod, event.getNotificationType());

        Mockito.verify(notificationService).createNotification(Mockito.any(Notification.class));
    }
}
