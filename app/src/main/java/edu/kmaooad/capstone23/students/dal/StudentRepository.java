package edu.kmaooad.capstone23.students.dal;

import edu.kmaooad.capstone23.students.parser.CSVStudent;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StudentRepository implements PanacheMongoRepository<Student> {
    public List<Student> insert(List<CSVStudent> students){
        List<Student> result = new ArrayList<>();
        for (CSVStudent student : students) {
            result.add(map(student));
        }
        persist(result);
        return result;
    }

    public Optional<Student> findById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return findByIdOptional(objectId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Student map(CSVStudent student){
        Student result = new Student();
        result.firstName = student.getFirstName();
        result.middleName = student.getMiddleName();
        result.lastName = student.getLastName();
        result.DOBTimestamp = student.getDOBTimestamp();
        result.email = student.getEmail();
        return result;
    }
}
