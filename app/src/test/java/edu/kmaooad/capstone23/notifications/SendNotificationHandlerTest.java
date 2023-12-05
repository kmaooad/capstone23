package edu.kmaooad.capstone23.notifications;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.SendNotification;
import edu.kmaooad.capstone23.notifications.dal.NEvent;
import edu.kmaooad.capstone23.notifications.dal.NType;
import edu.kmaooad.capstone23.notifications.events.NSent;
import edu.kmaooad.capstone23.notifications.handlers.SendNHandler;
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
    SendNHandler h;
    @Inject
    UserRepository userRepository;

    @Test
    @DisplayName("Send notification: success")
    public void testSendNotificationSuccess() {
        User u = UserMocks.validUser();
        userRepository.insert(u);
        SendNotification sendNotification = new SendNotification();
        sendNotification.setUserId(u.id.toString());
        sendNotification.setNotificationType(NType.EMAIL);
        sendNotification.setNotificationEvent(NEvent.COURSE_UPDATED);

        Result<NotificationSent> result = h.handle(sendNotification);
        Assertions.assertTrue(result.isSuccess());
    }
}