package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.notification.commands.SetNotificationPreferenceCommand;
import edu.kmaooad.capstone23.notification.dal.NotificationDestination;
import edu.kmaooad.capstone23.notification.dal.NotificationPreferencesRepository;
import edu.kmaooad.capstone23.notification.handlers.SetNotificationPreferenceHandler;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SetNotificationPrefsHandlerTests {

    @Inject
    UserRepository userRepository;

    @Inject
    NotificationPreferencesRepository prefRepository;

    User user;


    @Inject
    SetNotificationPreferenceHandler handler;

    @BeforeEach
    public void setup() {
        User user1 = new User();
        user1.id = new ObjectId();
        user1.firstName = "a";
        user1.lastName = "b";
        user1.email = "foo@gmail.com";

        this.user = userRepository.insert(user1);
    }

    @AfterEach
    public void tearDown() {
        this.userRepository.deleteAll();
        this.prefRepository.deleteAll();
    }

    @Test
    public void testSuccessfulCreation() {
        SetNotificationPreferenceCommand command = new SetNotificationPreferenceCommand();
        command.destination = NotificationDestination.EMAIL;
        command.eventType = "ORG_CREATED";
        command.userId = user.id.toString();

        var res = handler.handle(command);

        Assertions.assertTrue(res.isSuccess());
        Assertions.assertEquals(res.getValue().userId, user.id);
    }

    @Test
    public void testUserNotFound() {
        SetNotificationPreferenceCommand command = new SetNotificationPreferenceCommand();
        command.destination = NotificationDestination.EMAIL;
        command.eventType = "ORG_CREATED";
        command.userId = new ObjectId().toString();

        var res = handler.handle(command);

        Assertions.assertFalse(res.isSuccess());
    }

}
