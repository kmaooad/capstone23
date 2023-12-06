package edu.kmaooad.capstone23.users.controllers;

import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SetUserNotificationStatusControllerTest {

    private String idToUpdate;

    @Inject
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new UserMocks().validUser();
        userRepository.insert(user);
        idToUpdate = user.id.toString();
    }

    @Test
    @DisplayName("Set user notification preferences")
    public void testBasicUserNotificationPreferencesCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("userId", idToUpdate);
        jsonAsMap.put("notificationTypes", Collections.singletonList(NotificationType.EMAIL));

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when().post("/users/notification-preferences")
                .then()
                .statusCode(200);
    }
}