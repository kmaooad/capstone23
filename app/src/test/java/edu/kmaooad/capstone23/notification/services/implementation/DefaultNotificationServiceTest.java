package edu.kmaooad.capstone23.notification.services.implementation;

import edu.kmaooad.capstone23.notification.dal.Notification;
import edu.kmaooad.capstone23.notification.services.NotificationService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class DefaultNotificationServiceTest {

    @Inject
    NotificationService service;

    @Test
    void insert() {
        service.insert(new Notification());
    }

    @Test
    void findNotificationBy() {
    }
}