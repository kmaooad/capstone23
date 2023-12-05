package edu.kmaooad.capstone23.notification.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateNotificationControllerTest {

    private static final String Notification_CREATION_ENDPOINT = "/notification/create";
    private static final String APPLICATION_JSON = "application/json";
    private static final int STATUS_OK = 200;

    private Map<String, Object> validPayload;

    @Test
    @DisplayName("Create notification: valid input")
    public void testBasicNotificationCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("notificationAbout", "DeleteRelationJobToCompetences");
        jsonAsMap.put("notificationContent", "Було видалено зв'язок");
        jsonAsMap.put("sendingProgramToUse", "email");

        given()
                .contentType(APPLICATION_JSON)
                .body(jsonAsMap)
                .when()
                .post(Notification_CREATION_ENDPOINT)
                .then()
                .statusCode(STATUS_OK);
    }
}
