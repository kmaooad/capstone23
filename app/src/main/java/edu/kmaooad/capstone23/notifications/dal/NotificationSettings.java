package edu.kmaooad.capstone23.notifications.dal;

import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import io.quarkus.mongodb.panache.common.MongoEntity;
import java.util.Set;
import org.bson.types.ObjectId;

@MongoEntity(collection = "notification_settings")
public class NotificationSettings {
  public ObjectId userId;

  public Set<NotificationPreference> preferences;

  public Set<NotificationType> types;
}
