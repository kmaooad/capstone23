package edu.kmaooad.capstone23.notifications.controllers;

import edu.kmaooad.capstone23.notifications.dal.EventType;
import edu.kmaooad.capstone23.notifications.dal.NotificationMethod;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateNotificationControllerTest {
    @Test
    @DisplayName("Create notifications")
    public void testNotification() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("eventType", EventType.ACTIVATE_JOB);
        jsonAsMap.put("notificationMethod", NotificationMethod.EMAIL);
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/notifications/create")
                .then()
                .statusCode(200);
    }
}
