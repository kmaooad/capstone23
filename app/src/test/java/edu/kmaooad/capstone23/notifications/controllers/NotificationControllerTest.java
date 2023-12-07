package edu.kmaooad.capstone23.notifications.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class NotificationControllerTest {

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
    public void testCreateNotification() {
        var userId = getUser();
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", userId);
        jsonAsMap.put("type", "SMS");
        jsonAsMap.put("command", "remind");
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("notification/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Notification with non-existing user")
    public void testNotificationFakeUser() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", "1");
        jsonAsMap.put("type", "SMS");
        jsonAsMap.put("command", "remind");
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("notification/create")
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Create Notification with non-existing type")
    public void testNotificationFakeType() {
        var userId = getUser();
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", userId);
        jsonAsMap.put("type", "TWITTER");
        jsonAsMap.put("command", "remind");
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("notification/create")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create Notification with the repeating command")
    public void testNotificationRepeatingCommand() {
        var userId = getUser();
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", userId);
        jsonAsMap.put("method", "SMS");
        jsonAsMap.put("command", "remind");
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("notification/create")
                .then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("notification/create")
                .then()
                .statusCode(400);
    }


}

