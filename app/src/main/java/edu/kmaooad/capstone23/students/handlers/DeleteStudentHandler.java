package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.DeleteStudent;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentDeleted;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteStudentHandler implements CommandHandler<DeleteStudent, StudentDeleted> {

    @Inject
    StudentRepository studentRepository;

    @Override
    public Result<StudentDeleted> handle(DeleteStudent command) {
        var optionalStudent = studentRepository.findByIdOptional(command.getObjectId());
        if (optionalStudent.isEmpty()) {
            return new Result<>(ErrorCode.NOT_FOUND, "Student not found");
        }
        studentRepository.delete(optionalStudent.get());

        return new Result<>(new StudentDeleted(command.getObjectId()));
    }
}
