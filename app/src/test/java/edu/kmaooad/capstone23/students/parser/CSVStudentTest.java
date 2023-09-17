package edu.kmaooad.capstone23.students.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CSVStudentTest {

    @Test
    @DisplayName("Test CSVStudent equals: Success")
    public void testCSVStudentsEqualsSuccess() {
        CSVStudent expected = new CSVStudent(
                "Ivan",
                "",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        CSVStudent result = new CSVStudent(
                "Ivan",
                "",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertEquals(expected.hashCode(),result.hashCode());
        Assertions.assertEquals(expected,result);
    }

    @Test
    @DisplayName("Test CSVStudent equals: Fail")
    public void testCSVStudentsEqualsFail() {
        CSVStudent expected = new CSVStudent(
                "Ivan",
                "",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        CSVStudent result = new CSVStudent(
                "Ivan",
                "Oleksandrovych",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertNotEquals(expected.hashCode(),result.hashCode());
        Assertions.assertNotEquals(expected,result);
    }
}
