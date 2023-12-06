package edu.kmaooad.capstone23.notification;

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
public class CreateNotificationHandlerTest {
    @Inject
    private CreateNotificationHandler handler;
    @Inject
    private UserRepository repository;

    @Test
    @DisplayName("Create notification")
    public void createNotificationTest(){
        User user = UserMocks.validUser();
        repository.insert(user);
        AddNotification command = new AddNotification();
        command.setUserId(user.id.toString());
        command.setNotificationSendMethod(NotificationSendMethod.TEXT);
        command.setType(NotificationType.CHAT_NOTIFICATION);
        command.setMessage("Chat notification");
        Assertions.assertTrue(handler.handle(command).isSuccess());
    }
}
