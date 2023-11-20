package edu.kmaooad.capstone23.students.services;

import edu.kmaooad.capstone23.students.commands.FindStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentRepository studentRepository;

    @Override
    public List<Student> insert(List<CSVStudent> csvStudents) {
        return studentRepository.insert(csvStudents);
    }

    @Override
    public List<Student> find(FindStudent query) {
        return studentRepository.find(query);
    }

    @Override
    public Optional<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student findById(ObjectId id) {
        return studentRepository.findById(id);
    }

    @Override
    public void update(List<Student> studentsToUpdate) {
        studentRepository.update(studentsToUpdate);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
