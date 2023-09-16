package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.io.File;

@RequestScoped
public class CreateStudentHandler implements CommandHandler<CreateStudent, StudentCreated> {
//    @Inject
//    StudentRepository studentRepository;

    @Override
    public Result<StudentCreated> handle(CreateStudent command) {
        return null;
    }
}
