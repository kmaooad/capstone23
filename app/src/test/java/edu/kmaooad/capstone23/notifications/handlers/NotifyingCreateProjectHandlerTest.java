package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.competences.commands.CreateProj;
import edu.kmaooad.capstone23.competences.events.ProjCreated;
import edu.kmaooad.capstone23.notifications.NotificationMethod;
import edu.kmaooad.capstone23.notifications.NotificationObserver;
import edu.kmaooad.capstone23.notifications.NotificationSubject;
import edu.kmaooad.capstone23.notifications.SendImitator;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class NotifyingCreateProjectHandlerTest {

    @Inject
    SendImitator sendImitator;

    @Inject
    CommandHandler<CreateProj, ProjCreated> decoratedHandler;

    @Inject
    NotificationSubject notificationSubject;

    @Inject
    SubscribeToNotificationHandler subscribeToNotificationHandler;

    @Test
    public void testSendingNotifications() {
        System.out.println("TESTTTTT");
        CreateProj command = new CreateProj();
        command.setName("STUDENT_NAMEEE");
        command.setDescription("STUDENT_DESCRIPTIONNNN");
        command.setSkills(new LinkedList<>());
        command.setSkills(new LinkedList<>());
        decoratedHandler.handle(command);

        var sentMessages = sendImitator.getSentMessages();
        assertNotNull(sentMessages.get(NotificationMethod.EMAIL));
        assertNotNull(sentMessages.get(NotificationMethod.TELEGRAM));
        assertNotNull(sentMessages.get(NotificationMethod.SMS));
    }

    @BeforeEach
    public void setUp() {
        for (NotificationMethod method : NotificationMethod.values()) {
            NotificationObserver observer = subscribeToNotificationHandler.toObserver(method);
            notificationSubject.attach(observer);
        }
    }

    @AfterEach
    public void tearDown() {
        for (NotificationMethod method : NotificationMethod.values()) {
            NotificationObserver observer = subscribeToNotificationHandler.toObserver(method);
            notificationSubject.detach(observer);
        }
    }
}