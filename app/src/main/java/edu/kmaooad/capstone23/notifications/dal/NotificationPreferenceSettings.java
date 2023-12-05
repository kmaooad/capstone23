package edu.kmaooad.capstone23.notifications.dal;

import java.util.Set;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "notification_preference_settings")
public class NotificationPreferenceSettings {
    public ObjectId userId;

    public Set<NotificationPreference> preferences;
}
