package edu.kmaooad.capstone23.students.parser;

import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateCSVStudentTest {

    private static final UpdateCSVStudent UPDATE_STUDENT = new UpdateCSVStudent(
            new ObjectId("61dc2d31bbe643fc32022a5f"),
            "Ivan",
            "",
            "Dobrovolskyi",
            1039132800000L,
            "ivan.dobrovolskyi@ukma.edu.ua"
    );

    @Test
    @DisplayName("Test UpdateCSVStudent equals: success")
    public void testUpdateCSVStudentEqualsSuccess() {
        UpdateCSVStudent result = new UpdateCSVStudent(
                new ObjectId("61dc2d31bbe643fc32022a5f"),
                "Ivan",
                "",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertEquals(UPDATE_STUDENT.hashCode(), result.hashCode());
        Assertions.assertEquals(UPDATE_STUDENT, result);
    }

    @Test
    @DisplayName("Test UpdateCSVStudent equals: success same object")
    public void testUpdateCSVStudentEqualsSuccessSameObject() {
        UpdateCSVStudent result = UPDATE_STUDENT;
        Assertions.assertEquals(UPDATE_STUDENT.hashCode(), result.hashCode());
        Assertions.assertEquals(UPDATE_STUDENT, result);
    }

    @Test
    @DisplayName("Test UpdateCSVStudent not equals: Invalid ID")
    public void testUpdateCSVStudentEqualsFailID() {
        UpdateCSVStudent result = new UpdateCSVStudent(
                new ObjectId("61dc2d31bbe643fc32022a5a"),
                "Ivan",
                "",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertNotEquals(UPDATE_STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(UPDATE_STUDENT, result);
    }

    @Test
    @DisplayName("Test UpdateCSVStudent not equals: Invalid first name")
    public void testUpdateCSVStudentEqualsFailFirstName() {
        UpdateCSVStudent result = new UpdateCSVStudent(
                new ObjectId("61dc2d31bbe643fc32022a5f"),
                "",
                "",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertNotEquals(UPDATE_STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(UPDATE_STUDENT, result);
    }

    @Test
    @DisplayName("Test UpdateCSVStudent not equals: Invalid middle name")
    public void testUpdateCSVStudentEqualsFailMiddleName() {
        UpdateCSVStudent result = new UpdateCSVStudent(
                new ObjectId("61dc2d31bbe643fc32022a5f"),
                "Ivan",
                "MiddleName",
                "Dobrovolskyi",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertNotEquals(UPDATE_STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(UPDATE_STUDENT, result);
    }

    @Test
    @DisplayName("Test UpdateCSVStudent not equals: Invalid last name")
    public void testUpdateCSVStudentEqualsFailLastName() {
        UpdateCSVStudent result = new UpdateCSVStudent(
                new ObjectId("61dc2d31bbe643fc32022a5f"),
                "Ivan",
                "",
                "Noname",
                1039132800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertNotEquals(UPDATE_STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(UPDATE_STUDENT, result);
    }

    @Test
    @DisplayName("Test UpdateCSVStudent not equals: Invalid DOB")
    public void testUpdateCSVStudentEqualsFailDOB() {
        UpdateCSVStudent result = new UpdateCSVStudent(
                new ObjectId("61dc2d31bbe643fc32022a5f"),
                "Ivan",
                "",
                "Dobrovolskyi",
                1039152800000L,
                "ivan.dobrovolskyi@ukma.edu.ua"
        );
        Assertions.assertNotEquals(UPDATE_STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(UPDATE_STUDENT, result);
    }

    @Test
    @DisplayName("Test UpdateCSVStudent not equals: Invalid email")
    public void testUpdateCSVStudentEqualsFailEmail() {
        UpdateCSVStudent result = new UpdateCSVStudent(
                new ObjectId("61dc2d31bbe643fc32022a5f"),
                "Ivan",
                "",
                "Dobrovolskyi",
                1039132800000L,
                "test@ukma.edu.ua"
        );
        Assertions.assertNotEquals(UPDATE_STUDENT.hashCode(), result.hashCode());
        Assertions.assertNotEquals(UPDATE_STUDENT, result);
    }

    @Test
    @DisplayName("Test UpdateCSVStudent not equals: Null")
    public void testUpdateCSVStudentEqualsFailNull() {
        UpdateCSVStudent result = null;
        Assertions.assertNotEquals(UPDATE_STUDENT, result);
    }

    @Test
    @DisplayName("Test UpdateCSVStudent not equals: Different object")
    public void testUpdateCSVStudentEqualsFailObjectType() {
        Integer result = 1;
        Assertions.assertNotEquals(UPDATE_STUDENT, result);
    }
}
