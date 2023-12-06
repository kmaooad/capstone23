package edu.kmaooad.capstone23.students.services;

import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.students.commands.FindStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> insert(List<CSVStudent> csvStudents);

    List<Student> find(FindStudent query);

    Optional<Student> findById(String id);
    Student findById(ObjectId id);

    void update(List<Student> studentsToUpdate);

    void delete(Student student);
}
