package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.ReadStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentRead;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class FindStudentByIdHandler implements CommandHandler<ReadStudent, StudentRead> {
    @Inject
    StudentRepository studentRepository;

    @Override
    public Result<StudentRead> handle(ReadStudent command) {
        Optional<Student> student = studentRepository.findById(command.getId());
        if (student.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Student with this ID does not exist");

        StudentRead result = new StudentRead(List.of(student.get()));
        return new Result<>(result);
    }
}
