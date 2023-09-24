package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateStudentHandler implements CommandHandler<CreateStudent, StudentCreated> {

    @Inject
    private StudentRepository repository;

    public Result<StudentCreated> handle(CreateStudent command) {

        Student student = new Student(command.getStudentName());

        repository.insert(student);

        StudentCreated result = new StudentCreated(student.id.toString());

        return new Result<StudentCreated>(result);
    }
}