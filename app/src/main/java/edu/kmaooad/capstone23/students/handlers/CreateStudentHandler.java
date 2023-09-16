package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentsCreated;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import edu.kmaooad.capstone23.students.parser.CSVStudentParser;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
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
        List<CSVStudent> csvStudents;
        try {
            csvStudents = parser.parse(command.csvFile);
        } catch (ParseException e) {
            return new Result<>(ErrorCode.EXCEPTION, "Incorrect date format");
        } catch (FileNotFoundException e) {
            return new Result<>(ErrorCode.NOT_FOUND, "Can't find file " + command.csvFile.getName());
        } catch (IOException e) {
            return new Result<>(ErrorCode.EXCEPTION, e.getLocalizedMessage());
        }

        List<Student> students = repository.insert(csvStudents);

        List<ObjectId> studentIds = new ArrayList<>();
        for (Student student : students){
            studentIds.add(student.id);
        }

        StudentsCreated result = new StudentsCreated(studentIds);
        return new Result<>(result);
    }
}
