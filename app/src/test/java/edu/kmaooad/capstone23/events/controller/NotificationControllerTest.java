package edu.kmaooad.capstone23.events.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class NotificationControllerTest {

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

    @Test
    @DisplayName("Create Notification")
    public void testSuccessfulNotification() {
        var userId = createUser();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", userId);
        jsonAsMap.put("method", "SMS");
        jsonAsMap.put("command", "command");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("notification/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Notification with same command")
    public void testNotificationSameCommand() {
        var userId = createUser();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", userId);
        jsonAsMap.put("method", "SMS");
        jsonAsMap.put("command", "command");

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

    @Test
    @DisplayName("Create Notification with not exist user")
    public void testNotificationNotExistUser() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", "1");
        jsonAsMap.put("method", "SMS");
        jsonAsMap.put("command", "command");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("notification/create")
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Create Notification with not exist method")
    public void testNotificationNotExistMethod() {
        var userId = createUser();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("userId", userId);
        jsonAsMap.put("method", "VIBER");
        jsonAsMap.put("command", "command");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("notification/create")
                .then()
                .statusCode(400);
    }
}

