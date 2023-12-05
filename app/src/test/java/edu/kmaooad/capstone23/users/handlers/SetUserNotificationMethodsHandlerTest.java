package edu.kmaooad.capstone23.users.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.models.NotificationMethod;
import edu.kmaooad.capstone23.users.commands.SetUserNotificationMethods;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.events.UserNotificationMethodsSetted;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;


@QuarkusTest
public class SetUserNotificationMethodsHandlerTest {

    private String userId;
    @Inject
    UserRepository userRepository;
    @Inject
    CommandHandler<SetUserNotificationMethods, UserNotificationMethodsSetted> handler;

    @BeforeEach
    void setUp() {
        User user = UserMocks.validUser();
        userRepository.insert(user);
        userId = user.id.toHexString();
    }

    @Test
    @DisplayName("Should not have any notification methods by default")
    public void shouldNotHaveAnyNotificationMethodsByDefault() {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElse(null);

        assert user != null;
        Assertions.assertTrue(user.notificationMethods == null || user.notificationMethods.isEmpty());
    }

    @Test
    public void testSettingNotificationMethods() {
        SetUserNotificationMethods command = new SetUserNotificationMethods();
        command.setUserId(userId);
        ArrayList<NotificationMethod> notificationMethods = new ArrayList<>();
        notificationMethods.add(NotificationMethod.EMAIL);
        notificationMethods.add(NotificationMethod.TELEGRAM);
        command.setNotificationMethods(notificationMethods);

        Result<UserNotificationMethodsSetted> result = handler.handle(command);

        String userId = result.getValue().getUserId();
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElse(null);
        assert user != null;
        Assertions.assertTrue(user.notificationMethods.contains(NotificationMethod.EMAIL));
        Assertions.assertTrue(user.notificationMethods.contains(NotificationMethod.TELEGRAM));
        Assertions.assertFalse(user.notificationMethods.contains(NotificationMethod.SMS));
    }
}
