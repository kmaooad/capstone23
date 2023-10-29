package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.ReadStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentService;
import edu.kmaooad.capstone23.students.events.StudentRead;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class FindStudentByIdHandler implements CommandHandler<ReadStudent, StudentRead> {
    @Inject
    StudentService studentService;

    @Override
    public Result<StudentRead> handle(ReadStudent command) {
        Student student = studentService.findById(command.getId());
        if (student == null)
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Student with this ID does not exist");

        StudentRead result = new StudentRead(List.of(student));
        return new Result<>(result);
    }
}
