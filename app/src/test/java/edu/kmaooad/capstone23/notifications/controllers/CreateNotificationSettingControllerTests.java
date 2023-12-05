package edu.kmaooad.capstone23.notifications.controllers;

import edu.kmaooad.capstone23.notifications.dal.NotificationSettingRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateNotificationSettingControllerTests {

    @Inject
    NotificationSettingRepository repository;

    @BeforeEach
    void setup(){
        repository.deleteAll();
    }

    @AfterEach
    void clear(){
        repository.deleteAll();
    }

    @Test
    public void createValidNotificationSetting(){
        Map<String, Object> notificationSetting = new HashMap<>();
        notificationSetting.put("notificationType", "EMAIL");
        notificationSetting.put("notificationEvent", "CV_CREATED");

        given().contentType("application/json")
                .body(notificationSetting)
                .when()
                .post("/notifications/create")
                .then()
                .statusCode(200);
    }
}
