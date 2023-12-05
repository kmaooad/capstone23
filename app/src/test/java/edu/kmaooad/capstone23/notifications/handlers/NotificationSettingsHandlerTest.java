package edu.kmaooad.capstone23.notifications.handlers;

import com.google.inject.Inject;
import com.mongodb.assertions.Assertions;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.UpdateNotificationSettings;
import edu.kmaooad.capstone23.notifications.dal.NotificationSettings;
import edu.kmaooad.capstone23.notifications.events.NotificationSettingsUpdated;
import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.notifications.services.NotificationSettingsService;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import java.util.HashSet;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * NotificationSettingsHandlerTest
 */
public class NotificationSettingsHandlerTest
    extends HandlerTest<NotificationSettings, UpdateNotificationSettings,
                        NotificationSettingsUpdated> {
  @Inject NotificationService notificationService;

  @Inject NotificationSettingsService notificationMailService;

  @Inject UserRepository userRepository;

  ObjectId userId;


  @Test
  @DisplayName("successfuly updates notification settings")
  public void testUpdateNotificationSettings() {
    var prefs = new HashSet<NotificationPreference>();
    prefs.add(NotificationPreference.EMAIL);
    var types = new HashSet<NotificationType>();
    types.add(NotificationType.CHAT_PARTICIPANT_CREATED);
    types.add(NotificationType.PROFESSOR_ACTIVITY_ASSIGNED);
    var res = new NotificationSettings();
    res.userId = userId;
    res.preferences = prefs;
    res.types = types;
    Result<NotificationSettingsUpdated> result = run(res);

    assertHandled(result);
  }

  @Override
  protected void initCommand() {
    var user = new User();
    user.firstName = "firstName";
    user.lastName = "lastName";
    user.email = "email";
    user.phoneNumber = "phoneNumber";
    userRepository.insert(user);

    command = new UpdateNotificationSettings();
  }

  @Override
  protected void insertPayload(NotificationSettings entity) {
    var prefs = new HashSet<NotificationPreference>();
    prefs.add(NotificationPreference.EMAIL);
    var types = new HashSet<NotificationType>();
    types.add(NotificationType.CHAT_PARTICIPANT_CREATED);
    types.add(NotificationType.PROFESSOR_ACTIVITY_ASSIGNED);
    notificationMailService.setPreference(userId, prefs, types);

    command.setTypes(types);
    command.setPreferences(prefs);
    command.setUserId(userId);
  }
}
