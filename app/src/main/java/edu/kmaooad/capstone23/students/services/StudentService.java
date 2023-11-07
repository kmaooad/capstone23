package edu.kmaooad.capstone23.students.services;

import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@ApplicationScoped
public class StudentService {

    @Inject
    StudentRepository studentRepository;

    public Student findById(ObjectId id) {
       return studentRepository.findById(id);
    }
}
