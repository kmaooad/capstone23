package edu.kmaooad.capstone23.notification.handler;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.notification.commands.CreateNotification;
import edu.kmaooad.capstone23.notification.event.NotificationCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateNotificationHandlerTest {
    @Inject
    CommandHandler<CreateNotification, NotificationCreated> handler;

    @Test
    void testSuccessfulHandling() {
        CreateNotification command = new CreateNotification(new ObjectId(),
                "DeleteRelationJobToCompetences",
                "Привіт, ми видалили звязок роботи",
                "email");

        Result<NotificationCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }


}
