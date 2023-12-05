package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import static edu.kmaooad.capstone23.common.Mocks.mockValidEmail;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateNotificationHandlerTest {
    @Inject
    private CommandHandler<CreateNotification, NotificationCreated> notificationHandler;
    @Inject
    private UserRepository userRepository;

    @Test
    @DisplayName("Create notification")
    public void addNotificationTest(){
        User user = new User();
        user.firstName = "Peter";
        user.lastName = "Pan";
        user.email = mockValidEmail();
        userRepository.insert(user);

        CreateNotification command = new CreateNotification();
        command.setUserId(user.id.toString());
        command.setNotificationMethod("EMAIL");
        command.setEventType("ACTIVITY_CREATED");
        Assertions.assertTrue(notificationHandler.handle(command).isSuccess());
    }
}
