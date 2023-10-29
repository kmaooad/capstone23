package edu.kmaooad.capstone23.students.dal;

import edu.kmaooad.capstone23.students.commands.FindStudent;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class StudentService {

    @Inject
    StudentMongoRepository repository;

    public List<Student> insert(List<CSVStudent> students) { return repository.insert(students); }
    public void update(Iterable<Student> students) { repository.update(students); }

    public List<Student> find(FindStudent query) { return repository.find(query); }

    public Student findById(ObjectId id) { return repository.findById(id); }
    public Student findById(String id) { return repository.findById(id); }

    public long deleteAll() { return repository.deleteAll(); }

    public void persist(Student student) { repository.persist(student); }

    public boolean deleteById(ObjectId objectId) { return repository.deleteById(objectId); }
}
