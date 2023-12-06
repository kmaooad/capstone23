package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.UpdateStudent;
import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentNotified;
import edu.kmaooad.capstone23.students.events.StudentUpdated;
import edu.kmaooad.capstone23.students.events.StudentsUpdated;
import edu.kmaooad.capstone23.students.parser.UpdateCSVStudent;
import edu.kmaooad.capstone23.students.parser.UpdateCSVStudentParser;
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
public class UpdateStudentHandler implements CommandHandler<UpdateStudent, StudentsUpdated> {
    @Inject
    StudentService studentService;

    @Inject
    UpdateCSVStudentParser parser;

    @Inject
    NotifyStudentHandler notifyStudentHandler;

    @Override
    public Result<StudentsUpdated> handle(UpdateStudent command) {
        if (!command.csvFile.contentType().equals("text/csv"))
            return new Result<>(ErrorCode.EXCEPTION, "Incorrect file type");

        List<UpdateCSVStudent> csvStudents;
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

        List<Student> studentsToUpdate = new ArrayList<>();

        for (UpdateCSVStudent parsedStudent : csvStudents) {
            Student student = studentService.findById(parsedStudent.getId());

            if (parsedStudent.getLastName() != null)
                student.lastName = parsedStudent.getLastName();

            if (parsedStudent.getFirstName() != null)
                student.firstName = parsedStudent.getFirstName();

            if (parsedStudent.getMiddleName() != null)
                student.middleName = parsedStudent.getMiddleName();

            if (parsedStudent.getEmail() != null)
                student.email = parsedStudent.getEmail();

            if (parsedStudent.getDOBTimestamp() != 0)
                student.DOBTimestamp = parsedStudent.getDOBTimestamp();

            studentsToUpdate.add(student);
        }

        studentService.update(studentsToUpdate);

        List<StudentUpdated> studentsUpdated = new ArrayList<>();
        for (Student student : studentsToUpdate) {
            NotifyStudent notifyStudent = new NotifyStudent.Update(student);
            Result<StudentNotified> studentNotifiedResult = notifyStudentHandler.handle(notifyStudent);
            StudentUpdated studentUpdated = new StudentUpdated(student.id, studentNotifiedResult);
            studentsUpdated.add(studentUpdated);
        }

        StudentsUpdated result = new StudentsUpdated(studentsUpdated);
        return new Result<>(result);
    }
}
