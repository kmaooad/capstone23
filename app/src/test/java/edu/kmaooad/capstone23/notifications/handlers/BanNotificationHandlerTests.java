package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
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
public class BanNotificationHandlerTests {

    @Inject
    private CommandHandler<ScheduleNotification, NotificationScheduled> notificationsHandler;
    @Inject
    private MembersRepository membersRepository;
    @Inject
    private CommandHandler<BanEntity, EntityBanned> banHandler;

    @Test
    @DisplayName("Notifications : Schedule notification")
    public void scheduleNotificationTest() {
        Member member = new Member();
        member.userId = new ObjectId();
        member.orgId = new ObjectId();
        membersRepository.insert(member);

        var createCommand = new ScheduleNotification();
        createCommand.setNotificationType(NotificationType.email.toString());
        createCommand.setNotificationTriggerType(NotificationTriggerType.USER_BANNED.toString());
        createCommand.setMemberId(member.id.toString());

        var result1 = notificationsHandler.handle(createCommand);
        Assertions.assertTrue(result1.isSuccess());

        var banCommand = new BanEntity();
        banCommand.setEntityId(member.id);
        banCommand.setEntityType(BannedEntityType.Member.toString());
        banCommand.setReason("banned");
        var result2 = banHandler.handle(banCommand);
        Assertions.assertTrue(result2.isSuccess());
    }
}
