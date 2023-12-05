package edu.kmaooad.capstone23.notification;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notification.commands.CreateNotification;
import edu.kmaooad.capstone23.notification.events.NotificationCreated;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateNotificationHandlerTests {
    @Inject
    private CommandHandler<CreateNotification, NotificationCreated> handler;
    @Inject
    private UserRepository repository;

    @Test
    @DisplayName("Add notification: Basic")
    public void addNotificationTest(){
        User user = UserMocks.validUser();
        repository.insert(user);
        CreateNotification command = new CreateNotification();
        command.setUserId(user.id.toString());
        command.setType("ADDED_TO_CHAT");
        command.setSendingMethod("SMS");
        Assertions.assertTrue(handler.handle(command).isSuccess());
    }
}