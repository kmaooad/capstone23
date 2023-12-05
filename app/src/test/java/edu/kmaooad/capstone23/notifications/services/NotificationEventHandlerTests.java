package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notification.commands.SetNotificationPreferenceCommand;
import edu.kmaooad.capstone23.notification.dal.NotificationDestination;
import edu.kmaooad.capstone23.notification.dal.NotificationPreferencesRepository;
import edu.kmaooad.capstone23.notification.handlers.SetNotificationPreferenceHandler;
import edu.kmaooad.capstone23.notification.services.NotificationEventHandler;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
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
public class NotificationEventHandlerTests {

    User user;

    @Inject
    private UserRepository userRepository;

    @Inject
    private NotificationPreferencesRepository prefRepo;

    @Inject
    private NotificationEventHandler notificationEventHandler;

    @Inject
    SetNotificationPreferenceHandler setPrefHandler;

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
        this.prefRepo.deleteAll();
    }

    @Test
    public void testSuccessfulSending() {
        SetNotificationPreferenceCommand command = new SetNotificationPreferenceCommand();
        command.destination = NotificationDestination.EMAIL;
        command.eventType = "ORG_CREATED";
        command.userId = user.id.toString();

        var createdPref = setPrefHandler.handle(command);

        var result = notificationEventHandler.handle("ORG_CREATED", new OrgCreated("1"));

        Assertions.assertTrue(result);
    }

    @Test
    public void testNothingSent() {
        var result = notificationEventHandler.handle("ORG_CREATED",  new OrgCreated("1"));

        Assertions.assertFalse(result);
    }
}
