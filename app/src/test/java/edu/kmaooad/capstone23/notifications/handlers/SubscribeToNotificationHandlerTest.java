package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.competences.commands.CreateProj;
import edu.kmaooad.capstone23.competences.events.ProjCreated;
import edu.kmaooad.capstone23.competences.handlers.CreateProjectHandler;
import edu.kmaooad.capstone23.notifications.NotificationMethod;
import edu.kmaooad.capstone23.notifications.NotificationObserver;
import edu.kmaooad.capstone23.notifications.NotificationSubject;
import edu.kmaooad.capstone23.notifications.commands.SubscribeToNotification;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class SubscribeToNotificationHandlerTest {
    @Inject
    NotificationSubject notificationSubject;

    @Inject
    SubscribeToNotificationHandler subscribeToNotificationHandler;

    @Test
    public void testNoMethodIsSubscribeAtTheBeginning() {
        for (NotificationMethod method : NotificationMethod.values()) {
            NotificationObserver observer = subscribeToNotificationHandler.toObserver(method);
            assertFalse(notificationSubject.isAttached(observer));
        }
    }

    @Test
    public void testEveryMethodIsSubscribed() {
        subscribeAllMethods();

        for (NotificationMethod method : NotificationMethod.values()) {
            NotificationObserver observer = subscribeToNotificationHandler.toObserver(method);
            assertTrue(notificationSubject.isAttached(observer));
        }

        detachAll();
    }

    private void subscribeAllMethods() {
        for (NotificationMethod method : NotificationMethod.values()) {
            subscribeToNotificationHandler.handle(new SubscribeToNotification(method));
        }
    }

    private void detachAll() {
        for (NotificationMethod method : NotificationMethod.values()) {
            NotificationObserver observer = subscribeToNotificationHandler.toObserver(method);
            notificationSubject.detach(observer);
        }
    }
}