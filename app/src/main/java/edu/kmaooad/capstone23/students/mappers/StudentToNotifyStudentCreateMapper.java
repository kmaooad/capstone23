package edu.kmaooad.capstone23.students.mappers;

import edu.kmaooad.capstone23.common.Mapper;
import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentToNotifyStudentCreateMapper implements Mapper<Student, NotifyStudent.Create>{
    @Override
    public NotifyStudent.Create map(Student student) {
        return new NotifyStudent.Create(student.id, student.email, student.firstName);
    }
}