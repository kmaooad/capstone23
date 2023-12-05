package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.notifications.commands.SetupNotification;
import edu.kmaooad.capstone23.notifications.dal.NotificationOnWhatEventProceed;
import edu.kmaooad.capstone23.notifications.dal.NotificationPlaceToSend;
import edu.kmaooad.capstone23.notifications.dal.SetupedNotification;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProceedNotificationHandlerTests {

    @Inject
    private CommandHandler<SetupNotification, SetupedNotification> notificationsHandler;
    @Inject
    private MembersRepository membersRepository;
    @Inject
    private CommandHandler<BanEntity, EntityBanned> banHandler;

    private Member createAndInsertMember() {
        Member member = new Member();
        member.userId = new ObjectId();
        member.orgId = new ObjectId();
        membersRepository.insert(member);
        return member;
    }

    private SetupNotification createSetupNotificationCommand(Member member) {
        var command = new SetupNotification();
        command.setNotificationType(NotificationPlaceToSend.EMAIL.toString());
        command.setNotificationTriggerType(NotificationOnWhatEventProceed.USER_BANNED.toString());
        command.setMemberId(member.id.toString());
        return command;
    }

    private BanEntity createBanEntityCommand(Member member) {
        var command = new BanEntity();
        command.setEntityId(member.id);
        command.setEntityType(BannedEntityType.Member.toString());
        command.setReason("banned");
        return command;
    }

    @Test
    @DisplayName("Notifications : Schedule notifications")
    public void scheduleNotificationTest() {
        Member member = createAndInsertMember();
        SetupNotification createCommand = createSetupNotificationCommand(member);
        BanEntity banCommand = createBanEntityCommand(member);

        var result1 = notificationsHandler.handle(createCommand);
        Assertions.assertTrue(result1.isSuccess(), "Scheduling notification should succeed");

        var result2 = banHandler.handle(banCommand);
        Assertions.assertTrue(result2.isSuccess(), "Handling ban command should succeed");
    }
}
