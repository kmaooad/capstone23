package edu.kmaooad.capstone23.students.dal;

import java.util.Optional;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.activities.dal.Activity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepository implements PanacheMongoRepository<Student> {
    public Optional<Student> findById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return findByIdOptional(objectId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Student insert(Student student) {
        persist(student);
        return student;
    }
}
