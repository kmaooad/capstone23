package edu.kmaooad.capstone23.notifications.services;

import com.fasterxml.jackson.databind.ObjectWriter.Prefetch;
import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.notifications.services.provider.EmailNotificationService;
import edu.kmaooad.capstone23.notifications.services.provider.SMSNotificationService;
import edu.kmaooad.capstone23.notifications.services.provider.TGNotificationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

/**
 * NotificationServiceImpl
 */
@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {
  @Inject private NotificationSettingsService settingsService;

  @Inject private EmailNotificationService emailService;

  @Inject private SMSNotificationService smsService;

  @Inject private TGNotificationService tgService;

  @Override
  public void send(NotificationType type, ObjectId userId, String message) {
    var settings = settingsService.getSettings(userId);

    if (!settings.types.contains(type)) {
      return;
    }

    for (var preference : settings.preferences) {
      var provider = getProvider(preference);
      if (provider == null) {
        continue;
      }
      provider.send(type, userId, message);
    }
  }

  private NotificationProvider getProvider(NotificationPreference type) {
    switch (type) {
    case SMS:
      return this.smsService;
    case EMAIL:
      return this.emailService;
    case TG:
      return this.tgService;
    default:
      return null;
    }
  }
}
