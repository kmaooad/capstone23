package edu.kmaooad.capstone23.activities.dal;

import java.util.Optional;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActivityRepository implements PanacheMongoRepository<Activity> {
    public Optional<Activity> findById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return findByIdOptional(objectId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Activity insert(Activity activity) {
        persist(activity);
        return activity;
    }
}
