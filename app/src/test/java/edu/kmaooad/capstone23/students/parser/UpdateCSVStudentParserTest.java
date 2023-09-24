package edu.kmaooad.capstone23.students.parser;

import edu.kmaooad.capstone23.students.parser.exceptions.NotEnoughValues;
import edu.kmaooad.capstone23.students.parser.exceptions.TooManyValues;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@QuarkusTest
public class UpdateCSVStudentParserTest {
    @Inject
    UpdateCSVStudentParser updateCSVStudentParser;

    private static final UpdateCSVStudent UPDATE_STUDENT = new UpdateCSVStudent(
            new ObjectId("61dc2d31bbe643fc32022a5f"),
            "Ivan",
            "",
            "Dobrovolskyi",
            1039132800000L,
            "ivan.dobrovolskyi@ukma.edu.ua"
    );

    @Test
    @DisplayName("Update students' all fields from file: Successful")
    public void testUpdateStudentsAllFieldsParserSuccess() {
        Assertions.assertDoesNotThrow(() -> {
            File studentsFile = new File("src/test/resources/students/update/update_success.csv");
            UpdateCSVStudent[] expected = new UpdateCSVStudent[]{UPDATE_STUDENT};
            List<UpdateCSVStudent> result = updateCSVStudentParser.parse(studentsFile);
            Assertions.assertArrayEquals(expected, result.toArray());
        });
    }

    @Test
    @DisplayName("Update students' firstName field from file: ")
    public void testUpdateStudentsFirstNameFieldParserSuccess() {
        UpdateCSVStudent updateStudent = new UpdateCSVStudent();
        updateStudent.setId(new ObjectId("61dc2d31bbe643fc32022a5f"));
        updateStudent.setFirstName("Ivan");
        Assertions.assertDoesNotThrow(() -> {
            File studentsFile = new File("src/test/resources/students/update/update_firstname_success.csv");
            UpdateCSVStudent[] expected = new UpdateCSVStudent[]{updateStudent};
            List<UpdateCSVStudent> result = updateCSVStudentParser.parse(studentsFile);
            Assertions.assertArrayEquals(expected, result.toArray());
        });
    }

    @Test
    @DisplayName("Update students' middleName field from file: Success")
    public void testUpdateStudentsMiddleNameFieldParserSuccess() {
        UpdateCSVStudent updateStudent = new UpdateCSVStudent();
        updateStudent.setId(new ObjectId("61dc2d31bbe643fc32022a5f"));
        updateStudent.setMiddleName("Valeriiovych");
        Assertions.assertDoesNotThrow(() -> {
            File studentsFile = new File("src/test/resources/students/update/update_middlename_success.csv");
            UpdateCSVStudent[] expected = new UpdateCSVStudent[]{updateStudent};
            List<UpdateCSVStudent> result = updateCSVStudentParser.parse(studentsFile);
            Assertions.assertArrayEquals(expected, result.toArray());
        });
    }

    @Test
    @DisplayName("Update multiple students from file: Success")
    public void testUpdateMultipleStudentsSuccess() {
        UpdateCSVStudent u1 = new UpdateCSVStudent();
        u1.setId(new ObjectId("61dc2d31bbe643fc32022a5a"));
        u1.setDOBTimestamp(1039132800000L);
        u1.setEmail("ivan.dobrovolskyi@ukma.edu.ua");
        UpdateCSVStudent u2 = new UpdateCSVStudent();
        u2.setId(new ObjectId("61dc2d31bbe643fc32022a5b"));
        u2.setFirstName("Ivan");
        u2.setEmail("ivan.dobrovolskyi@ukma.edu.ua");
        UpdateCSVStudent u3 = new UpdateCSVStudent();
        u3.setId(new ObjectId("61dc2d31bbe643fc32022a5c"));
        u3.setDOBTimestamp(1039132800000L);
        UpdateCSVStudent u4 = new UpdateCSVStudent();
        u4.setId(new ObjectId("61dc2d31bbe643fc32022a5d"));
        u4.setMiddleName("Mykolaiovych");
        UpdateCSVStudent u6 = new UpdateCSVStudent();
        u6.setId(new ObjectId("61dc2d31bbe643fc32022a5f"));
        u6.setLastName("Kostenko");
        u6.setEmail("test.email@ukma.edu.ua");

        Assertions.assertDoesNotThrow(() -> {
            File studentsFile = new File("src/test/resources/students/update/update_multiple_success.csv");
            UpdateCSVStudent[] expected = new UpdateCSVStudent[]{
                    u1, u2, u3, u4, u6
            };
            List<UpdateCSVStudent> result = updateCSVStudentParser.parse(studentsFile);
            Assertions.assertArrayEquals(expected, result.toArray());
        });
    }

    @Test
    @DisplayName("Update students from file: Not enough fields")
    public void testUpdateStudentsNotEnoughFields() {
        Assertions.assertThrows(NotEnoughValues.class, () -> {
            File studentsFile = new File("src/test/resources/students/update/update_multiple_success.csv");
            updateCSVStudentParser.parse(studentsFile);
        });
    }

    @Test
    @DisplayName("Update students from file: Id is not present")
    public void testUpdateStudentsIdIsNotPresentFields() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            File studentsFile = new File("src/test/resources/students/too_many_fields.csv");
            updateCSVStudentParser.parse(studentsFile);
        });
    }

    @Test
    @DisplayName("Update students from file: Too many fields present")
    public void testUpdateStudentsTooManyFieldsFields() {
        Assertions.assertThrows(TooManyValues.class, () -> {
            File studentsFile = new File("src/test/resources/students/update/too_many_fields.csv");
            updateCSVStudentParser.parse(studentsFile);
        });
    }

    @Test
    @DisplayName("Parse students from file: File not found")
    public void testsStudentsParserFileNotFount() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            File studentsFile = new File("src/test/resources/students/no_file.csv");
            updateCSVStudentParser.parse(studentsFile);
        });
    }

    @Test
    @DisplayName("Parse students from file: Successful with empty file")
    public void testsStudentsParserSuccessWithEmptyFile() {
        Assertions.assertDoesNotThrow(() -> {
            File studentsFile = new File("src/test/resources/students/success_empty.csv");
            UpdateCSVStudent[] expected = new UpdateCSVStudent[]{};
            List<UpdateCSVStudent> result = updateCSVStudentParser.parse(studentsFile);
            Assertions.assertArrayEquals(expected, result.toArray());
        });
    }

}
