package edu.kmaooad.capstone23.notifications.handlers;

import java.util.Set;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.UpdateNotificationSettings;
import edu.kmaooad.capstone23.notifications.events.NotificationSettingsUpdated;
import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.notifications.services.NotificationSettingsService;
import edu.kmaooad.capstone23.common.ErrorCode;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateNotificationSettingsHandler implements CommandHandler<UpdateNotificationSettings, NotificationSettingsUpdated> {

  @Inject
  NotificationSettingsService notificationSettingsService;

	@Override
	public Result<NotificationSettingsUpdated> handle(UpdateNotificationSettings command) {
    try {
      Set<NotificationPreference> preferences = command.getPreferences();
      Set<NotificationType> types = command.getTypes();

      notificationSettingsService.setPreference(command.getUserId(), preferences, types);

      return new Result<>(new NotificationSettingsUpdated(true));
    } catch (Exception e) {
      return new Result<>(ErrorCode.EXCEPTION, e.getMessage());
    }
	}
}
