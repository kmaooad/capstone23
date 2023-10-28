package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.MockFileUpload;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.events.StudentCreated;
import edu.kmaooad.capstone23.students.events.StudentsCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

@QuarkusTest
public class CreateStudentHandlerTest {

    @Inject
    CreateStudentHandler handler;

    @Test
    @DisplayName("Create students from csv: Successful")
    public void testStudentsCreationFromCSVSuccess() {
        CreateStudent createStudent = new CreateStudent();
        createStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/success.csv"), "text/csv");
        Result<StudentsCreated> result = handler.handle(createStudent);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(result.getValue().students().size(), 1);
    }

    @Test
    @DisplayName("Create students from csv: Incorrect file type")
    public void testStudentsCreationFromCSVIncorrectType() {
        CreateStudent createStudent = new CreateStudent();
        createStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/incorrect_type.json"), "application/json");
        Result<StudentsCreated> result = handler.handle(createStudent);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.EXCEPTION);
    }

    @Test
    @DisplayName("Create students from csv: Incorrect date format")
    public void testStudentsCreationFromCSVIncorrectDateFormat() {
        CreateStudent createStudent = new CreateStudent();
        createStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/incorrect_date_format.csv"), "text/csv");
        Result<StudentsCreated> result = handler.handle(createStudent);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.EXCEPTION);
    }

    @Test
    @DisplayName("Create students from csv: Not found")
    public void testsStudentsCreationFromCSVNotFound() {
        CreateStudent createStudent = new CreateStudent();
        createStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/not_found.csv"), "text/csv");
        Result<StudentsCreated> result = handler.handle(createStudent);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.NOT_FOUND);
    }

    @Test
    @DisplayName("Create students from csv: Not enough fields")
    public void testsStudentsCreationFromCSVNotEnoughFields() {
        CreateStudent createStudent = new CreateStudent();
        createStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/not_enough_fields.csv"), "text/csv");
        Result<StudentsCreated> result = handler.handle(createStudent);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.EXCEPTION);
    }

    @Test
    @DisplayName("Create students from csv: Too many fields")
    public void testsStudentsCreationFromCSVTooManyFields() {
        CreateStudent createStudent = new CreateStudent();
        createStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/too_many_fields.csv"), "text/csv");
        Result<StudentsCreated> result = handler.handle(createStudent);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.EXCEPTION);
    }

    @Test
    @DisplayName("Create students from csv: Contains empty string")
    public void testsStudentsCreationFromCSVContainsEmptyString() {
        CreateStudent createStudent = new CreateStudent();
        createStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/contains_empty_strings.csv"), "text/csv");
        Result<StudentsCreated> result = handler.handle(createStudent);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(result.getValue().students().size(), 2);
    }

    @Test
    @DisplayName("Create students from csv: Invalid email")
    public void testsStudentsCreationFromCSVInvalidEmail() {
        CreateStudent createStudent = new CreateStudent();
        createStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/invalid_email.csv"), "text/csv");
        Result<StudentsCreated> result = handler.handle(createStudent);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.VALIDATION_FAILED);
    }

    @Test
    @DisplayName("Create students from csv: Notified successfully")
    public void testsStudentsCreationNotifySuccess() {
        CreateStudent createStudent = new CreateStudent();
        createStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/success.csv"), "text/csv");
        Result<StudentsCreated> result = handler.handle(createStudent);

        Assertions.assertTrue(result.isSuccess());
        List<StudentCreated> studentsCreated = result.getValue().students();
        Assertions.assertEquals(studentsCreated.size(), 1);
        for (StudentCreated studentCreated : studentsCreated) {
            Assertions.assertTrue(studentCreated.isNotified());
            Assertions.assertNull(studentCreated.notificationError());
        }
    }
}
