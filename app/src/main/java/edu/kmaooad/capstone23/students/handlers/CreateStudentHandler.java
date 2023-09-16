package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentsCreated;
import edu.kmaooad.capstone23.students.parser.CSVStudentParser;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CreateStudentHandler implements CommandHandler<CreateStudent, StudentsCreated> {
    @Inject
    StudentRepository repository;

    @Inject
    CSVStudentParser parser;

    @Override
    public Result<StudentsCreated> handle(CreateStudent command) {
        List<ObjectId> studentIds = new ArrayList<>();
        StudentsCreated result = new StudentsCreated(studentIds);
        return new Result<>(result);
    }
}
