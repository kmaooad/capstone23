package edu.kmaooad.capstone23.notifications.dal;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationPreferenceSettingsRepository implements PanacheMongoRepository<NotificationPreferenceSettings> {
    public NotificationPreferenceSettings insert(NotificationPreferenceSettings settings) {
        persist(settings);
        return settings;
    }

    public NotificationPreferenceSettings findByUserId(ObjectId userId) {
        return find("userId", userId).firstResult();
    }
}
