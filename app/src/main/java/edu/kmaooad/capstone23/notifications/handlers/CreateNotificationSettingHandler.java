package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.CreateNotificationSetting;
import edu.kmaooad.capstone23.notifications.dal.NotificationEvent;
import edu.kmaooad.capstone23.notifications.dal.NotificationSetting;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.events.NotificationSettingCreated;
import edu.kmaooad.capstone23.notifications.services.NotificationSettingService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateNotificationSettingHandler
        implements CommandHandler<CreateNotificationSetting, NotificationSettingCreated> {

    @Inject
    NotificationSettingService service;

    @Override
    public Result<NotificationSettingCreated> handle(CreateNotificationSetting createCommand) {
        NotificationEvent event;
        NotificationType type;

        try {
            event = NotificationEvent.valueOf(createCommand.getNotificationEvent());
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.NOT_FOUND, "event " + createCommand.getNotificationEvent() + " doesn't exist");
        }

        try {
            type = NotificationType.valueOf(createCommand.getNotificationType());
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.NOT_FOUND, "type " + createCommand.getNotificationType() + " doesn't exist");
        }

        NotificationSetting setting = new NotificationSetting(type, event);
        service.persist(setting);

        NotificationSettingCreated result = new NotificationSettingCreated(setting.id.toString());
        return new Result<>(result);
    }
}
