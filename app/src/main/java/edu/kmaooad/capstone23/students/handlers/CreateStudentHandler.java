package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Mapper;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentCreated;
import edu.kmaooad.capstone23.students.events.StudentNotified;
import edu.kmaooad.capstone23.students.events.StudentsCreated;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import edu.kmaooad.capstone23.students.parser.CreateCSVStudentParser;
import edu.kmaooad.capstone23.students.parser.exceptions.IncorrectValuesAmount;
import edu.kmaooad.capstone23.students.parser.exceptions.InvalidEmail;
import edu.kmaooad.capstone23.students.services.StudentService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CreateStudentHandler implements CommandHandler<CreateStudent, StudentsCreated> {
    @Inject
    StudentService studentService;

    @Inject
    CreateCSVStudentParser parser;

    @Inject
    NotifyStudentHandler notifyStudentHandler;

    @Inject
    Mapper<Student, NotifyStudent.Create> studentToNotificationMapper;

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

        List<Student> students = studentService.insert(csvStudents);

        List<StudentCreated> studentsCreated =
                students.stream()
                        .map(studentToNotificationMapper::map)
                        .map((notifyStudent) -> {
                            Result<StudentNotified> studentNotifiedResult = notifyStudentHandler.handle(notifyStudent);
                            return new StudentCreated(notifyStudent.getStudentId(), studentNotifiedResult);
                        })
                        .toList();

        StudentsCreated result = new StudentsCreated(studentsCreated);
        return new Result<>(result);
    }
}
