package edu.kmaooad.capstone23.students.parser;

import edu.kmaooad.capstone23.students.parser.exceptions.NotEnoughValues;
import edu.kmaooad.capstone23.students.parser.exceptions.TooManyValues;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
@QuarkusTest
public class CSVStudentParserTest {
    @Inject
    CreateCSVStudentParser parser;

    private static final CSVStudent STUDENT = new CSVStudent(
            "Ivan",
            "",
            "Dobrovolskyi",
            1039132800000L,
            "ivan.dobrovolskyi@ukma.edu.ua"
    );

    @Test
    @DisplayName("Parse students from file: Successful")
    public void testStudentsParserSuccess() {
        Assertions.assertDoesNotThrow(() -> {
            File studentsFile = new File("src/test/resources/students/success.csv");
            CSVStudent[] expected = new CSVStudent[]{ STUDENT };
            List<CSVStudent> result = parser.parse(studentsFile);
            Assertions.assertArrayEquals(expected, result.toArray());
        });
    }

    @Test
    @DisplayName("Parse students from file: Successful with empty strings")
    public void testsStudentsParserSuccessWithBlankStrings() {
        Assertions.assertDoesNotThrow(() -> {
            File studentsFile = new File("src/test/resources/students/contains_empty_strings.csv");

            CSVStudent[] expected = new CSVStudent[]{STUDENT, STUDENT};
            List<CSVStudent> result = parser.parse(studentsFile);
            Assertions.assertArrayEquals(expected, result.toArray());
        });
    }

    @Test
    @DisplayName("Parse students from file: Successful with empty file")
    public void testsStudentsParserSuccessWithEmptyFile() {
        Assertions.assertDoesNotThrow(() -> {
            File studentsFile = new File("src/test/resources/students/success_empty.csv");
            CSVStudent[] expected = new CSVStudent[]{};
            List<CSVStudent> result = parser.parse(studentsFile);
            Assertions.assertArrayEquals(expected, result.toArray());
        });
    }

    @Test
    @DisplayName("Parse students from file: Incorrect date format")
    public void testStudentsParserIncorrectDateFormat() {
        Assertions.assertThrows(ParseException.class,() -> {
            File studentsFile = new File("src/test/resources/students/incorrect_date_format.csv");
            parser.parse(studentsFile);
        });
    }

    @Test
    @DisplayName("Parse students from file: File not found")
    public void testsStudentsParserFileNotFount() {
        Assertions.assertThrows(FileNotFoundException.class,() -> {
            File studentsFile = new File("src/test/resources/students/no_file.csv");
            parser.parse(studentsFile);
        });
    }

    @Test
    @DisplayName("Parse students from file: not enough fields")
    public void testsStudentsParserNotEnoughFields() {
        Assertions.assertThrows(NotEnoughValues.class,() -> {
            File studentsFile = new File("src/test/resources/students/not_enough_fields.csv");
            parser.parse(studentsFile);
        });
    }

    @Test
    @DisplayName("Parse students from file: too many fields")
    public void testsStudentsParserTooManyFields() {
        Assertions.assertThrows(TooManyValues.class,() -> {
            File studentsFile = new File("src/test/resources/students/too_many_fields.csv");
            parser.parse(studentsFile);
        });
    }
}
