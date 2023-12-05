package edu.kmaooad.capstone23.notification.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface NotificationPreferencesRepository extends PanacheMongoRepository<NotificationPreference> {
}
