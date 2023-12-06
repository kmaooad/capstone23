package edu.kmaooad.capstone23.notifications;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.SendNotification;
import edu.kmaooad.capstone23.notifications.dal.NotificationEvent;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.events.NotificationSent;
import edu.kmaooad.capstone23.notifications.handlers.SendNotificationHandler;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SendNotificationHandlerTest {
    @Inject
    SendNotificationHandler handler;
    @Inject
    UserRepository userRepository;

    @Test
    @DisplayName("Send notification: success")
    public void testSendNotificationSuccess() {
        User user = UserMocks.validUser();
        userRepository.insert(user);
        SendNotification sendNotification = new SendNotification();
        sendNotification.setUserId(user.id.toString());
        sendNotification.setNotificationType(NotificationType.EMAIL);
        sendNotification.setNotificationEvent(NotificationEvent.COURSE_UPDATED);

        Result<NotificationSent> result = handler.handle(sendNotification);
        Assertions.assertTrue(result.isSuccess());
    }
}
