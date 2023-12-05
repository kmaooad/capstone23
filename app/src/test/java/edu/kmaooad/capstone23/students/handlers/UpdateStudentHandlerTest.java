package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.MockFileUpload;
import edu.kmaooad.capstone23.students.commands.UpdateStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentsUpdated;
import edu.kmaooad.capstone23.students.parser.CSVStudent;
import edu.kmaooad.capstone23.students.parser.CreateCSVStudentParser;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@QuarkusTest
public class UpdateStudentHandlerTest {

    @Inject
    UpdateStudentHandler handler;

    @Inject
    StudentRepository repository;
    @Inject
    CreateCSVStudentParser createCSVStudentParser;

    private ObjectId idToUpdate;

    @BeforeEach
    void setup() {
        try {
            List<CSVStudent> csvStudents = createCSVStudentParser.parse(new File("src/test/resources/students/success.csv"));
            List<Student> students = repository.insert(csvStudents);
            idToUpdate = students.get(0).id;
        } catch (Exception ignored) {

        }
    }

    @Test
    @DisplayName("Update students from csv: Success")
    public void testStudentsUpdateFromCSVSuccess() {
        String path = "src/test/resources/students/update/testStudentsUpdateFirstNameFromCSVSuccess.csv";
        String student = idToUpdate + ",Makii,,,06/12/2002,ivan.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        UpdateStudent updateStudent = new UpdateStudent();
        updateStudent.csvFile = new MockFileUpload(new File(path), "text/csv");
        Result<StudentsUpdated> result = handler.handle(updateStudent);
        File file = new File(path);
        file.delete();
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(result.getValue().students().size(), 1);
    }

    @Test
    @DisplayName("Update student's middle name from csv: Success")
    public void testStudentsUpdateMiddleNameFromCSVSuccess() {
        String path = "src/test/resources/students/update/testStudentsUpdateMiddleNameFromCSVSuccess.csv";
        String student = idToUpdate + ",,Kyrylovych,,06/12/2002,ivan.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        UpdateStudent updateStudent = new UpdateStudent();
        updateStudent.csvFile = new MockFileUpload(new File(path), "text/csv");
        Result<StudentsUpdated> result = handler.handle(updateStudent);
        File file = new File(path);
        file.delete();
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(result.getValue().students().size(), 1);
    }

    @Test
    @DisplayName("Update student's last name from csv: Success")
    public void testStudentsUpdateLastNameFromCSVSuccess() {
        String path = "src/test/resources/students/update/testStudentsUpdateLastNameFromCSVSuccess.csv";
        String student = idToUpdate + ",,,Petrenko,06/12/2002,ivan.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        UpdateStudent updateStudent = new UpdateStudent();
        updateStudent.csvFile = new MockFileUpload(new File(path), "text/csv");
        Result<StudentsUpdated> result = handler.handle(updateStudent);
        File file = new File(path);
        file.delete();
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(result.getValue().students().size(), 1);
    }

    @Test
    @DisplayName("Update student's DOB from csv: Success")
    public void testStudentsUpdateDOBFromCSVSuccess() {
        String path = "src/test/resources/students/update/testStudentsUpdateDOBFromCSVSuccess.csv";
        String student = idToUpdate + ",,,,12/12/2002,ivan.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        UpdateStudent updateStudent = new UpdateStudent();
        updateStudent.csvFile = new MockFileUpload(new File(path), "text/csv");
        Result<StudentsUpdated> result = handler.handle(updateStudent);
        File file = new File(path);
        file.delete();
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(result.getValue().students().size(), 1);
    }

    @Test
    @DisplayName("Update student's email from csv: Success")
    public void testStudentsUpdateEmailFromCSVSuccess() {
        String path = "src/test/resources/students/update/testStudentsUpdateEmailFromCSVSuccess.csv";
        String student = idToUpdate + ",,,,,ivan2.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        UpdateStudent updateStudent = new UpdateStudent();
        updateStudent.csvFile = new MockFileUpload(new File(path), "text/csv");
        Result<StudentsUpdated> result = handler.handle(updateStudent);
        File file = new File(path);
        file.delete();
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(result.getValue().students().size(), 1);
    }

    @Test
    @DisplayName("Update student's email from csv: Invalid email")
    public void testStudentsUpdateEmailFromCSVInvalidEmail() {
        String path = "src/test/resources/students/update/testStudentsUpdateEmailFromCSVInvalidEmail.csv";
        String student = idToUpdate + ",,,,,ivan2.dobrovolskyi@";
        writeToFile(student, path);
        UpdateStudent updateStudent = new UpdateStudent();
        updateStudent.csvFile = new MockFileUpload(new File(path), "text/csv");
        Result<StudentsUpdated> result = handler.handle(updateStudent);
        File file = new File(path);
        file.delete();
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.VALIDATION_FAILED);
    }

    @Test
    @DisplayName("Update students from csv: Invalid DOB")
    public void testStudentsUpdateFromCSVInvalidDOB() {
        String path = "src/test/resources/students/update/testStudentsUpdateFromCSVInvalidDOB.csv";
        String student = idToUpdate + ",Makii,,,12/2002,ivan.dobrovolskyi@ukma.edu.ua";
        writeToFile(student, path);
        UpdateStudent updateStudent = new UpdateStudent();
        updateStudent.csvFile = new MockFileUpload(new File(path), "text/csv");
        Result<StudentsUpdated> result = handler.handle(updateStudent);
        File file = new File(path);
        file.delete();
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.EXCEPTION);
    }


    @Test
    @DisplayName("Update students from csv: Not enough fields")
    public void testStudentsUpdateFromCSVNotEnoughFields() {
        UpdateStudent updateStudent = new UpdateStudent();
        updateStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/update/missing_id.csv"), "text/csv");
        Result<StudentsUpdated> result = handler.handle(updateStudent);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.EXCEPTION);
    }

    @Test
    @DisplayName("Update students from csv: Too many fields")
    public void testStudentsUpdateFromCSVTooManyFields() {
        UpdateStudent updateStudent = new UpdateStudent();
        updateStudent.csvFile = new MockFileUpload(new File("src/test/resources/students/update/too_many_fields.csv"), "text/csv");
        Result<StudentsUpdated> result = handler.handle(updateStudent);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.EXCEPTION);
    }

    public void writeToFile(String student, String pathStr) {
        try {
            FileWriter myWriter = new FileWriter(pathStr);
            myWriter.write(student);
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
