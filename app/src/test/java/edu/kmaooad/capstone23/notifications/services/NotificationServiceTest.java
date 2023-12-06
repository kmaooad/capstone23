package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.models.Event;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class NotificationServiceTest {

    @Inject
    private UserRepository userRepository;

    @Inject
    private NotificationService notificationService;

    private String userId;


    @BeforeEach
    void setUp() {
        User user = new UserMocks().validUser();
        userRepository.insert(user);
        userId = user.id.toString();
    }

    @Test
    @DisplayName("Send notification with invalid user id")
    void sendNotification_UserNotFound_ReturnsFalse() {
        String userId = "nonexistentUserId";
        String message = "TestMessage";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            notificationService.sendNotification(userId, Event.DEPARTMENT_CREATED, message);
        });
    }

    @Test
    @DisplayName("Send notification when user has no notification types")
    void sendNotification_UserWithNoNotificationTypes_ReturnsFalse() {
        String message = "TestMessage";
        boolean result = notificationService.sendNotification(userId, Event.USER_CREATED, message);
        assertFalse(result);
    }

    @Test
    @DisplayName("Send notification when user has notification types")
    void sendNotification_SuccessfullySendsNotifications_ReturnsTrue() {
        User userWithNotificationTypes = userRepository.findById(userId).get();
        userWithNotificationTypes.notificationTypes = new ArrayList<>(Collections.singletonList(NotificationType.EMAIL));
        boolean result = notificationService.sendNotification(userId, Event.USER_CREATED, "TestMessage");
        assertTrue(result);
    }
}
