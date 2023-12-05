package edu.kmaooad.capstone23.experts.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpertNotificationRepository implements PanacheMongoRepository<ExpertNotification> {
    public ExpertNotification insert(ExpertNotification expertNotification) {
        persist(expertNotification);
        return expertNotification;
    }
}
