package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
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
    private CommandHandler<CreateNotification, NotificationCreated> createNotificationHandler;
    @Inject
    private UserRepository userRepository;

    @Test
    @DisplayName("Notification Creation: Basic")
    public void addNotificationTest(){
        User user = createTestUser();
        CreateNotification command = new CreateNotification();
        command.setUserId(user.id.toString());
        command.setType("ADDED");
        command.setMethod("SMS");
        Assertions.assertTrue(createNotificationHandler.handle(command).isSuccess());
    }

    private User createTestUser() {
        User user = UserMocks.validUser();
        userRepository.insert(user);
        return user;
    }
}