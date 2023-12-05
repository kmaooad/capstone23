package edu.kmaooad.capstone23.notifications.handler_decorators;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationEvent;
import edu.kmaooad.capstone23.notifications.dal.NotificationSetting;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import edu.kmaooad.capstone23.notifications.observers.EmailNotificationObserver;
import edu.kmaooad.capstone23.notifications.observers.SmsNotificationObserver;
import edu.kmaooad.capstone23.notifications.observers.TelegramNotificationObserver;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.notifications.services.NotificationSettingService;
import edu.kmaooad.capstone23.notifications.utils.Notifying;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
@Notifying
public class NotifyingCreateCVHandler implements CommandHandler<CreateCV, CVCreated> {

    @Inject
    @Default
    CommandHandler<CreateCV, CVCreated> handler;

    @Inject
    NotificationSettingService settingService;

    @Inject
    NotificationService service;

    @Override
    public Result<CVCreated> handle(CreateCV command) {
        Result<CVCreated> result = handler.handle(command);
        if (!result.isSuccess())
            return result;

        List<NotificationSetting> settings = settingService.list("notificationEvent", NotificationEvent.CV_CREATED);

        for (NotificationSetting setting : settings) {
            NotificationType type = setting.notificationType;
            switch (type) {
                case EMAIL -> service.addObserver(new EmailNotificationObserver());
                case SMS -> service.addObserver(new SmsNotificationObserver());
                case TELEGRAM -> service.addObserver(new TelegramNotificationObserver());
            }
        }


        service.notify(new Notification("CV successfully created"));
        return result;
    }
}
