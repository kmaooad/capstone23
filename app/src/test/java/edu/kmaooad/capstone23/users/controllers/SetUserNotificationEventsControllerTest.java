package edu.kmaooad.capstone23.users.controllers;

import edu.kmaooad.capstone23.notifications.models.Event;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SetUserNotificationEventsControllerTest {
    private String userId;
    @Inject
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = UserMocks.validUser();
        userRepository.insert(user);
        userId = user.id.toHexString();
    }

    @Test
    @DisplayName("Should not have any notification events by default")
    public void shouldNotHaveAnyEventsByDefault() {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElse(null);

        assert user != null;
        Assertions.assertTrue(user.notificationEvents == null || user.notificationEvents.isEmpty());
    }

    @Test
    public void testSettingEvents() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        ArrayList<Event> notificationEvents = new ArrayList<>();
        notificationEvents.add(Event.SKILL_SET_CREATED);

        jsonAsMap.put("userId", userId);
        jsonAsMap.put("notificationEvents", notificationEvents);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when().post("/users/set-notification-events")
                .then()
                .statusCode(200);
    }

}