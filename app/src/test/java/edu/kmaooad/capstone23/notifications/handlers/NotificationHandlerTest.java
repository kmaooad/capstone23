package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class NotificationHandlerTest {

    @Inject
    CommandHandler<CreateNotification, NotificationCreated> notificationHandler;

    private String getUser() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "Jon");
        jsonAsMap.put("lastName", "Ron");
        jsonAsMap.put("email", "test@test.com");
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

    @Test
    @DisplayName("Create Notification")
    public void testNotification() {
        var userId = getUser();
        CreateNotification command = new CreateNotification();
        command.setRecipientId(userId);
        command.setDeliveryType("SMS");
        command.setActionCommand("remind");
        var result = notificationHandler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }


    @Test
    @DisplayName("Create Notification with non-existing user")
    public void testNotificationNonExistingUser() {
        CreateNotification command = new CreateNotification();
        command.setRecipientId("333");
        command.setDeliveryType("SMS");
        command.setActionCommand("remind");
        var result = notificationHandler.handle(command);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }

    @Test
    @DisplayName("Create Notification with non-existing type")
    public void testNotificationNonExistingType() {
        var userId = getUser();
        CreateNotification command = new CreateNotification();
        command.setRecipientId(userId);
        command.setDeliveryType("TWITTER");
        command.setActionCommand("remind");
        var result =  notificationHandler.handle(command);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }

    @Test
    @DisplayName("Create Notification with repeating command")
    public void testNotificationRepeatingCommand() {
        var userId = getUser();
        CreateNotification command = new CreateNotification();
        command.setRecipientId(userId);
        command.setDeliveryType("SMS");
        command.setActionCommand("remind");
        var result = notificationHandler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        var repeatResult = notificationHandler.handle(command);
        Assertions.assertFalse(repeatResult.isSuccess());
        Assertions.assertNull(repeatResult.getValue());
    }

}