package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.SetNotificationType;
import edu.kmaooad.capstone23.notifications.dal.NotificationTypeRepository;
import edu.kmaooad.capstone23.notifications.events.NotificationTypeSet;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class SetNotificationTypeHandlerTest {

    @Inject
    NotificationTypeRepository notificationTypeRepository;
    @Inject
    CommandHandler<SetNotificationType, NotificationTypeSet> handler;

    @AfterEach
    void tearDown() {
        notificationTypeRepository.deleteAll();
    }

    @Test
    void testSetNotificationHandle() {
        SetNotificationType command = new SetNotificationType();
        command.setUserId("usersuperUniqueIdForNotificationTest");
        command.setNotificationType("CREATE_ORG_MEMBER");
        command.setNotificationMethod("SMS");

        Result<NotificationTypeSet> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }
}