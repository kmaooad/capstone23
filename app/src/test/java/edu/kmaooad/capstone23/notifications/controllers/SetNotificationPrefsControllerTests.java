package edu.kmaooad.capstone23.notifications.controllers;

import edu.kmaooad.capstone23.notification.dal.NotificationDestination;
import edu.kmaooad.capstone23.notification.dal.NotificationPreferencesRepository;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SetNotificationPrefsControllerTests {

    @Inject
    UserRepository userRepository;

    @Inject
    NotificationPreferencesRepository prefRepository;

    User user;

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
        Map<String, Object> command = new HashMap<>();
        command.put("destination", NotificationDestination.EMAIL);
        command.put("eventType", "ORG_CREATED");
        command.put("userId", user.id.toString());

        given()
            .contentType("application/json")
            .body(command)
            .when()
            .post("/notification-preferences/set")
            .then()
            .statusCode(200);
    }

    @Test
    public void testUserNotFound() {
        Map<String, Object> command = new HashMap<>();
        command.put("destination", NotificationDestination.EMAIL);
        command.put("eventType", "ORG_CREATED");
        command.put("userId", new ObjectId().toString());

        given()
            .contentType("application/json")
            .body(command)
            .when()
            .post("/notification-preferences/set")
            .then()
            .statusCode(404);
    }

}
