package edu.kmaooad.capstone23.events.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.events.commands.CreateNotification;
import edu.kmaooad.capstone23.events.events.NotificationCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class NotificationHandlerTest {

    private String getRandEmail() {
        return "test" + new Random().nextInt(100000) + "@test.com";
    }


    private String createUser() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "TestUser");
        jsonAsMap.put("lastName", "TestUser");
        jsonAsMap.put("email", getRandEmail());

        return given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("users/create")
                .then()
                .statusCode(200)
                .extract()
                .path("id");
    }

    @Inject
    CommandHandler<CreateNotification, NotificationCreated> handler;

    @Test
    @DisplayName("Create Notification")
    public void testSuccessfulNotification() {
        var userId = createUser();

        CreateNotification command = new CreateNotification();
        command.setUserId(userId);
        command.setMethod("SMS");
        command.setCommand("command");

        var result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

    @Test
    @DisplayName("Create Notification with same command")
    public void testNotificationSameCommand() {
        var userId = createUser();

        CreateNotification command = new CreateNotification();
        command.setUserId(userId);
        command.setMethod("SMS");
        command.setCommand("command");

        var result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        var result2 = handler.handle(command);

        Assertions.assertFalse(result2.isSuccess());
        Assertions.assertNull(result2.getValue());
    }

    @Test
    @DisplayName("Create Notification with not exist user")
    public void testNotificationNotExistUser() {
        CreateNotification command = new CreateNotification();
        command.setUserId("236f3ea3994a7710cfb497a7");
        command.setMethod("SMS");
        command.setCommand("command");

        var result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }

    @Test
    @DisplayName("Create Notification with not exist method")
    public void testNotificationNotExistMethod() {
        var userId = createUser();

        CreateNotification command = new CreateNotification();
        command.setUserId(userId);
        command.setMethod("notExist");
        command.setCommand("command");

        var result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }
}
