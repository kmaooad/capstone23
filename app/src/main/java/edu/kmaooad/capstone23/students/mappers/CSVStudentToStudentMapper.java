package edu.kmaooad.capstone23.students.mappers;

import edu.kmaooad.capstone23.common.Mapper;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CSVStudentToStudentMapper implements Mapper<CSVStudent, Student> {
    @Override
    public Student map(CSVStudent csvStudent) {
        Student student = new Student();
        student.firstName = csvStudent.getFirstName();
        student.middleName = csvStudent.getMiddleName();
        student.lastName = csvStudent.getLastName();
        student.DOBTimestamp = csvStudent.getDOBTimestamp();
        student.email = csvStudent.getEmail();
        return student;
    }
}
