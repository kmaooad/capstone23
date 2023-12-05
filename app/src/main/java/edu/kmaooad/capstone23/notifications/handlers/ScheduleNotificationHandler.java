package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.notifications.dal.NotificationTriggerType;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.dal.ScheduledNotification;
import edu.kmaooad.capstone23.notifications.commands.ScheduleNotification;
import edu.kmaooad.capstone23.notifications.events.NotificationScheduled;
import edu.kmaooad.capstone23.notifications.services.NotificationSchedulingService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class ScheduleNotificationHandler implements CommandHandler<ScheduleNotification, NotificationScheduled> {

    @Inject
    private NotificationSchedulingService schedulingService;
    @Inject
    private MembersRepository membersRepository;

    @Override
    public Result<NotificationScheduled> handle(ScheduleNotification command) {
        if(membersRepository.findById(command.getMemberId()) == null){
            return new Result<>(ErrorCode.ENTITY_NOT_FOUND, "Specified user doesn't exist");
        }
        ScheduledNotification notification = new ScheduledNotification();
        notification.memberId = new ObjectId(command.getMemberId());
        notification.notificationType = NotificationType.valueOf(command.getNotificationType());
        notification.notificationTriggerType = NotificationTriggerType.valueOf(command.getNotificationTriggerType());

        schedulingService.insert(notification);

        return new Result<>(new NotificationScheduled(notification.memberId.toString(),
                notification.notificationType.toString(), notification.notificationTriggerType.toString()));
    }
}
