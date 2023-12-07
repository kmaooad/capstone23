package edu.kmaooad.capstone23.notifiications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifications.commands.PushNotificationCommand;
import edu.kmaooad.capstone23.notifications.events.NotificationPushed;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PushNotificationHandlerTests {
    @Inject
    CommandHandler<PushNotificationCommand, NotificationPushed> handler;
    @Inject
    UserRepository repository;

    @Test
    @DisplayName("Add notification: Basic")
    public void addNotificationTest(){
        User user = UserMocks.validUser();
        repository.insert(user);

        PushNotificationCommand command = new PushNotificationCommand();
        command.setUserId(user.id.toString());
        command.setNotificationEvent("USER_CREATED");
        command.setNotificationDestination("telegram");

        Assertions.assertTrue(handler.handle(command).isSuccess());
    }
}
