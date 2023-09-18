package edu.kmaooad.capstone23.activities.dal;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActivityRepository implements PanacheMongoRepository<Activity> {
    public Activity findById(String id) {
        ObjectId objectId = new ObjectId(id);
        return findByIdOptional(objectId).orElse(null);
    }

    public Activity insert(Activity activity) {
        persist(activity);
        return activity;
    }
}