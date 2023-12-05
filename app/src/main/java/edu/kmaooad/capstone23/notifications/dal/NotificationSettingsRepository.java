package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class NotificationSettingsRepository
    implements PanacheMongoRepository<NotificationSettings> {
  public NotificationSettings insert(NotificationSettings settings) {
    persist(settings);
    return settings;
  }

  public NotificationSettings findByUserId(ObjectId userId) {
    return find("userId", userId).firstResult();
  }
}
