package edu.kmaooad.capstone23.students.parser;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CSVStudentTest {

    private static final CSVStudent STUDENT = new CSVStudent(
            "Ivan",
            "",
            "Dobrovolskyi",
            1039132800000L,
            "ivan.dobrovolskyi@ukma.edu.ua"
    );

    @Test
    @DisplayName("Test CSVStudent equals: Success")
    public void testCSVStudentsEqualsSuccess() {
        CSVStudent result = new CSVStudent(
                "Ivan",
                "",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertEquals(STUDENT.hashCode(), result.hashCode());
        Assertions.assertEquals(STUDENT,result);
    }

    @Test
    @DisplayName("Test CSVStudent equals: Success same object")
    public void testCSVStudentsEqualsSuccessSameObject() {
        CSVStudent result = STUDENT;
        Assertions.assertEquals(STUDENT.hashCode(), result.hashCode());
        Assertions.assertEquals(STUDENT,result);
    }

    @Test
    @DisplayName("Test CSVStudent not equals: Different first name")
    public void testCSVStudentsEqualsFailFirstName() {
        CSVStudent result = new CSVStudent(
                "Ivan2",
                "",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertNotEquals(STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(STUDENT,result);
    }

    @Test
    @DisplayName("Test CSVStudent not equals: Different middle name")
    public void testCSVStudentsEqualsFailMiddleName() {
        CSVStudent result = new CSVStudent(
                "Ivan",
                "Oleksandrovych",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertNotEquals(STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(STUDENT,result);
    }

    @Test
    @DisplayName("Test CSVStudent not equals: Different last name")
    public void testCSVStudentsEqualsFailLastName() {
        CSVStudent result = new CSVStudent(
                "Ivan",
                "",
                "Dobrovolsky",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertNotEquals(STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(STUDENT,result);
    }

    @Test
    @DisplayName("Test CSVStudent not equals: Different date of birth")
    public void testCSVStudentsEqualsFailDOB() {
        CSVStudent result = new CSVStudent(
                "Ivan",
                "",
                "Dobrovolskyi",
                1039142800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertNotEquals(STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(STUDENT,result);
    }

    @Test
    @DisplayName("Test CSVStudent not equals: Different email")
    public void testCSVStudentsEqualsFailEmail() {
        CSVStudent result = new CSVStudent(
                "Ivan",
                "",
                "Dobrovolskyi",
                1039142800000L,
                "iryan2.melnyk@ukma.edu.ua"
        );
        Assertions.assertNotEquals(STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(STUDENT,result);
    }

    @Test
    @DisplayName("Test CSVStudent not equals: Null")
    public void testCSVStudentsEqualsFailNull() {
        CSVStudent result = null;
        Assertions.assertNotEquals(STUDENT, result);
    }

    @Test
    @DisplayName("Test CSVStudent not equals: Different object")
    public void testCSVStudentsEqualsFailObjectType() {
        Integer result = 1024;
        Assertions.assertNotEquals(STUDENT, result);
    }
}
