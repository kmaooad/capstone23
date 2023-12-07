package edu.kmaooad.capstone23.users.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class SetUserNotificationMethodsControllerTest {
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
        Map<String, Object> jsonAsMap = new HashMap<>();
        ArrayList<NotificationMethod> notificationMethods = new ArrayList<>();
        notificationMethods.add(NotificationMethod.SMS);

        jsonAsMap.put("userId", userId);
        jsonAsMap.put("notificationMethods", notificationMethods);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when().post("/users/set-notification-methods")
                .then()
                .statusCode(200);
    }

}

