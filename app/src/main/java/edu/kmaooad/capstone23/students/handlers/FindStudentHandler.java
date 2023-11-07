package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.FindStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentService;
import edu.kmaooad.capstone23.students.events.StudentsFound;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class FindStudentHandler implements CommandHandler<FindStudent, StudentsFound> {

    @Inject
    StudentService repository;

    @Override
    public Result<StudentsFound> handle(FindStudent command) {
        List<Student> students = repository.find(command);

        List<ObjectId> studentIds = new ArrayList<>();
        for (Student student : students) {
            studentIds.add(student.id);
        }

        StudentsFound result = new StudentsFound(studentIds);
        return new Result<>(result);
    }
}
