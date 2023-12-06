package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.dal.EventType;
import edu.kmaooad.capstone23.notifications.dal.NotificationMethod;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.notifications.handler.CreateNotificationHandler;
import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
@QuarkusTest
public class CreateNotificationHandlerTest {
    @Inject
    CommandHandler<CreateNotification, NotificationCreated> handler;

    @Test
    void testNotificationHandling() {
        CreateNotification command = new CreateNotification();
        command.setEventType(EventType.ACTIVATE_JOB);
        command.setNotificationMethod(NotificationMethod.EMAIL);

        Result<NotificationCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }
}
