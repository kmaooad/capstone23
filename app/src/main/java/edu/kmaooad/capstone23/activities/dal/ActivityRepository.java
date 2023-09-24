package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class ActivityRepository implements PanacheMongoRepository<ExtraActivity> {
    public ExtraActivity insert(ExtraActivity course) {
        persist(course);
        return course;
    }
}
