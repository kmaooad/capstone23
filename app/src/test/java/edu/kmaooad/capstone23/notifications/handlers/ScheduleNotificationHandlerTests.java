package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.notifications.commands.SetupNotification;
import edu.kmaooad.capstone23.notifications.dal.NotificationOnWhatEventProceed;
import edu.kmaooad.capstone23.notifications.dal.NotificationPlaceToSend;
import edu.kmaooad.capstone23.notifications.events.NotificationSetuped;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ScheduleNotificationHandlerTests {

    @Inject
    private CommandHandler<SetupNotification, NotificationSetuped> handler;
    @Inject
    private MembersRepository membersRepository;

    private Member createTestMember() {
        Member member = new Member();
        member.userId = new ObjectId();
        member.orgId = new ObjectId();
        return member;
    }

    @Test
    @DisplayName("Notifications : Schedule notifications")
    public void scheduleNotificationTest() {
        // Arrange: Create and insert a member
        Member member = createTestMember();
        membersRepository.insert(member);

        // Act: Create a setup notification command and handle it
        var command = new SetupNotification();
        command.setNotificationType(NotificationPlaceToSend.EMAIL.toString());
        command.setNotificationTriggerType(NotificationOnWhatEventProceed.USER_BANNED.toString());
        command.setMemberId(member.id.toString());
        var result = handler.handle(command);

        // Assert: Verify the result is successful
        Assertions.assertTrue(result.isSuccess(), "The notification setup should be successful");
    }
}
