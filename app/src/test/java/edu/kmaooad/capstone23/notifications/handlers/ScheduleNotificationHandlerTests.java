package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.notifications.commands.ScheduleNotification;
import edu.kmaooad.capstone23.notifications.dal.NotificationTriggerType;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.events.NotificationScheduled;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ScheduleNotificationHandlerTests {

    @Inject
    private CommandHandler<ScheduleNotification, NotificationScheduled> handler;
    @Inject
    private MembersRepository membersRepository;

    @Test
    @DisplayName("Notifications : Schedule notification")
    public void scheduleNotificationTest() {

        Member member = new Member();
        member.userId = new ObjectId();
        member.orgId = new ObjectId();
        membersRepository.insert(member);

        var command = new ScheduleNotification();
        command.setNotificationType(NotificationType.email.toString());
        command.setNotificationTriggerType(NotificationTriggerType.USER_BANNED.toString());
        command.setMemberId(member.id.toString());

        var result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
    }
}
