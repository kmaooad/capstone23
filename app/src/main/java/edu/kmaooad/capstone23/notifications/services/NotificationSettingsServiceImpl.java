package edu.kmaooad.capstone23.notifications.services;

import java.util.Set;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.notifications.dal.NotificationSettings;
import edu.kmaooad.capstone23.notifications.dal.NotificationSettingsRepository;
import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificationSettingsServiceImpl implements NotificationSettingsService {
  @Inject
  private NotificationSettingsRepository notificationSettingsRepository;

	@Override
	public NotificationSettings getSettings(ObjectId userId) {
    NotificationSettings settings = notificationSettingsRepository.findByUserId(userId);

    if (settings == null) {
      settings = new NotificationSettings();
      settings.userId = userId;
      notificationSettingsRepository.insert(settings);

      return settings;
    }

    return settings;
	}

	@Override
	public void setPreference(ObjectId userId, Set<NotificationPreference> preference, Set<NotificationType> types) {
    NotificationSettings settings = getSettings(userId);

    settings.types = types;
    settings.preferences = preference;

    notificationSettingsRepository.update(settings);
	}
}
