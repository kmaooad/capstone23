package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.notifications.commands.SetNotificationType;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.dal.NotificationTypeRepository;
import edu.kmaooad.capstone23.orgs.members.TestWithOrgSetUp;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateMemberNotificationHandlerTest extends TestWithOrgSetUp {

    @Inject
    NotificationTypeRepository notificationTypeRepository;

    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> handler;

    @BeforeEach
    void setUp() {
        NotificationType notificationType = new NotificationType();
        notificationType.userId = "usersuperUniqueIdForNotificationTest";
        notificationType.notificationType = "CREATE_ORG_MEMBER";
        notificationType.notificationMethod = "SMS";

        notificationTypeRepository.insert(notificationType);
    }

    @AfterEach
    void tearDown() {
        notificationTypeRepository.deleteAll();
    }

    @Test
    void testSuccessfulHandling() {
        CreateBasicMember command = new CreateBasicMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId.toString());
        command.setEmail("email@email.com");
        command.setIsExpert("false");

        Result<BasicMemberCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getMemberId().isEmpty());
    }
}