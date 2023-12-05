package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.notifications.commands.SetupNotification;
import edu.kmaooad.capstone23.notifications.dal.NotificationOnWhatEventProceed;
import edu.kmaooad.capstone23.notifications.dal.NotificationPlaceToSend;
import edu.kmaooad.capstone23.notifications.dal.SetupedNotification;
import edu.kmaooad.capstone23.notifications.events.NotificationSetuped;
import edu.kmaooad.capstone23.notifications.services.NotificationSchedulingService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class ScheduleNotificationHandler implements CommandHandler<SetupNotification, NotificationSetuped> {

    @Inject
    private NotificationSchedulingService schedulingService;
    @Inject
    private MembersRepository membersRepository;

    @Override
    public Result<NotificationSetuped> handle(SetupNotification command) {
        Member member = membersRepository.findById(command.getMemberId());
        if (member == null) {
            return new Result<>(ErrorCode.ENTITY_NOT_FOUND, "Specified user doesn't exist");
        }

        return processSetupNotification(command, member);
    }

    private Result<NotificationSetuped> processSetupNotification(SetupNotification command, Member member) {
        SetupedNotification notification = createSetupedNotification(command, member);
        schedulingService.insert(notification);

        return new Result<>(new NotificationSetuped(notification.memberId.toString(),
                notification.notificationPlaceToSend.toString(), notification.notificationOnWhatEventProceed.toString()));
    }

    private SetupedNotification createSetupedNotification(SetupNotification command, Member member) {
        SetupedNotification notification = new SetupedNotification();
        notification.memberId = new ObjectId(String.valueOf(member.id));
        notification.notificationPlaceToSend = NotificationPlaceToSend.valueOf(command.getNotificationType());
        notification.notificationOnWhatEventProceed = NotificationOnWhatEventProceed.valueOf(command.getNotificationTriggerType());

        return notification;
    }
}

