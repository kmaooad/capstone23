package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;

import java.util.Optional;

public class CourseRepository implements PanacheMongoRepository<Course> {
    public Optional<Course> findById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return findByIdOptional(objectId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Course insert(Course course) {
        persist(course);
        return course;
    }
}
