package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.UpdateStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentUpdated;
import edu.kmaooad.capstone23.students.parser.UpdateCSVStudent;
import edu.kmaooad.capstone23.students.parser.UpdateCSVStudentParser;
import edu.kmaooad.capstone23.students.parser.exceptions.IncorrectValuesAmount;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class UpdateStudentHandler implements CommandHandler<UpdateStudent, StudentUpdated> {
    @Inject
    StudentRepository repository;

    @Inject
    UpdateCSVStudentParser parser;

    @Override
    public Result<StudentUpdated> handle(UpdateStudent command) {
        if (!command.csvFile.contentType().equals("text/csv"))
            return new Result<>(ErrorCode.EXCEPTION, "Incorrect file type");

        List<UpdateCSVStudent> csvStudents;
        try {
            csvStudents = parser.parse(command.csvFile.uploadedFile().toFile());
        } catch (ParseException e) {
            return new Result<>(ErrorCode.EXCEPTION, "Incorrect date format");
        } catch (FileNotFoundException e) {
            return new Result<>(ErrorCode.NOT_FOUND, "Can't find file " + command.csvFile.uploadedFile().getFileName());
        } catch (IOException | IncorrectValuesAmount e) {
            return new Result<>(ErrorCode.EXCEPTION, e.getLocalizedMessage());
        }

        List<Student> studentsToUpdate = new ArrayList<>();

        for (UpdateCSVStudent parsedStudent : csvStudents) {
            Student student = repository.findById(parsedStudent.getId());

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

        repository.update(studentsToUpdate);
        List<ObjectId> studentsIds = studentsToUpdate.stream().map(student -> student.id).toList();

        StudentUpdated result = new StudentUpdated(studentsIds);
        return new Result<>(result);
    }
}
