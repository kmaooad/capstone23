package edu.kmaooad.capstone23.notifications;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifications.commands.AddNotification;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NotificationAdded;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AddNotificationHandlerTests {
    @Inject
    private CommandHandler<AddNotification, NotificationAdded> handler;
    @Inject
    private UserRepository repository;

    @Test
    @DisplayName("Add notification")
    public void addNotificationTest(){
        User user = UserMocks.validUser();
        repository.insert(user);
        AddNotification command = new AddNotification();
        command.setUserId(user.id.toString());
        command.setNotificationMethod("TELEGRAM");
        command.setEventType("ADDED_TO_CHAT");
        Assertions.assertTrue(handler.handle(command).isSuccess());
    }
}
