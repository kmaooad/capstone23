package edu.kmaooad.capstone23.students.dal;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepository implements PanacheMongoRepository<Student> {
    public Student findById(String id) {
        ObjectId objectId = new ObjectId(id);
        return findByIdOptional(objectId).orElse(null);
    }

    public Student insert(Student student) {
        persist(student);
        return student;
    }
}
