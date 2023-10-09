package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentsCreated;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import edu.kmaooad.capstone23.students.parser.CreateCSVStudentParser;
import edu.kmaooad.capstone23.students.parser.exceptions.IncorrectValuesAmount;
import edu.kmaooad.capstone23.students.parser.exceptions.InvalidEmail;
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
    CreateCSVStudentParser parser;

    @Override
    public Result<StudentsCreated> handle(CreateStudent command) {
        if (!command.csvFile.contentType().equals("text/csv"))
            return new Result<>(ErrorCode.EXCEPTION, "Incorrect file type");

        List<CSVStudent> csvStudents;
        try {
            csvStudents = parser.parse(command.csvFile.uploadedFile().toFile());
        } catch (ParseException e) {
            return new Result<>(ErrorCode.EXCEPTION, "Incorrect date format");
        } catch (InvalidEmail e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Email validation failed");
        } catch (FileNotFoundException e) {
            return new Result<>(ErrorCode.NOT_FOUND, "Can't find file " + command.csvFile.uploadedFile().getFileName());
        } catch (IOException | IncorrectValuesAmount e) {
            return new Result<>(ErrorCode.EXCEPTION, e.getLocalizedMessage());
        }

        List<Student> students = repository.insert(csvStudents);

        List<ObjectId> studentIds = new ArrayList<>();
        for (Student student : students) {
            studentIds.add(student.id);
        }

        StudentsCreated result = new StudentsCreated(studentIds);
        return new Result<>(result);
    }
}
