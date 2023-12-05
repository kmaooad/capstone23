package edu.kmaooad.capstone23.notifications.services;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import java.util.HashSet;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;

@QuarkusTest
public class NotificationServiceTest {
    @Inject
    NotificationService notificationService;

    @Inject
    NotificationSettingsService notificationMailService;
    
    @Inject
    UserRepository userRepository;

    ObjectId userId;

    @BeforeEach
    public void init() {
        var user = new User();
        user.firstName = "firstName";
        user.lastName = "lastName";
        user.email = "email";
        user.phoneNumber = "phoneNumber";
        userRepository.insert(user);
        
        userId = user.id;
        var prefs = new HashSet<NotificationPreference>();
        prefs.add(NotificationPreference.EMAIL);
        var types = new HashSet<NotificationType>();
        types.add(NotificationType.CHAT_PARTICIPANT_CREATED);
        types.add(NotificationType.PROFESSOR_ACTIVITY_ASSIGNED);
        notificationMailService.setPreference(userId, prefs, types);
    }

    @Test
    @DisplayName("Test send() not throw exception")
    public void testSendNotificationWithNullNotification() {
        Assertions.assertDoesNotThrow(() -> {
            notificationService.send(NotificationType.CHAT_PARTICIPANT_CREATED, userId, "body");
        });
    }
}
