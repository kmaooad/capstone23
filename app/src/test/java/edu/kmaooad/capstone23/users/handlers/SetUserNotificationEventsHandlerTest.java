package edu.kmaooad.capstone23.users.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.models.Event;
import edu.kmaooad.capstone23.users.commands.SetUserNotificationEvents;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.events.UserNotificationEventsSetted;
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
public class SetUserNotificationEventsHandlerTest {

    private String userId;
    @Inject
    UserRepository userRepository;
    @Inject
    CommandHandler<SetUserNotificationEvents, UserNotificationEventsSetted> handler;

    @BeforeEach
    void setUp() {
        User user = UserMocks.validUser();
        userRepository.insert(user);
        userId = user.id.toHexString();
    }

    @Test
    @DisplayName("Should not have any notification events by default")
    public void shouldNotHaveAnyNotificationEventsByDefault() {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElse(null);

        assert user != null;
        Assertions.assertTrue(user.notificationEvents == null || user.notificationEvents.isEmpty());
    }

    @Test
    public void testSettingNotificationEvents() {
        SetUserNotificationEvents command = new SetUserNotificationEvents();
        command.setUserId(userId);
        ArrayList<Event> notificationEvents = new ArrayList<>();
        notificationEvents.add(Event.SKILL_SET_CREATED);
        notificationEvents.add(Event.SKILL_SET_DELETED);
        command.setNotificationEvents(notificationEvents);

        Result<UserNotificationEventsSetted> result = handler.handle(command);

        String userId = result.getValue().getUserId();
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElse(null);
        assert user != null;
        Assertions.assertTrue(user.notificationEvents.contains(Event.SKILL_SET_CREATED));
        Assertions.assertTrue(user.notificationEvents.contains(Event.SKILL_SET_DELETED));
        Assertions.assertFalse(user.notificationEvents.contains(Event.SKILL_SET_UPDATED));
    }
}

