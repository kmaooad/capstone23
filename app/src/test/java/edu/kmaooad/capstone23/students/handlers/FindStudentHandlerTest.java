package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.FindStudent;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentsFound;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FindStudentHandlerTest {

    @Inject
    FindStudentHandler handler;

    @Inject
    StudentRepository repository;

    @BeforeEach
    public void setup() {
        repository.deleteAll();

        given()
                .multiPart("csvFile", new File("src/test/resources/students/find_init.csv"), "text/csv")
                .when()
                .post("/students/create_csv")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Find all students by default")
    public void testFindAllStudents() {
        FindStudent command = new FindStudent();
        Result<StudentsFound> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(3, result.getValue().students().size());
    }

    @Test
    @DisplayName("Find students by first name")
    public void testFindStudentsByFirstName() {
        FindStudent command = new FindStudent();
        command.setFirstName("Iva");
        Result<StudentsFound> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(3, result.getValue().students().size());
    }

    @Test
    @DisplayName("Find students by middle name")
    public void testFindStudentsByMiddleName() {
        FindStudent command = new FindStudent();
        command.setMiddleName("Oleksandrovych");
        Result<StudentsFound> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(2, result.getValue().students().size());
    }

    @Test
    @DisplayName("Find students by last name")
    public void testFindStudentsByLastName() {
        FindStudent command = new FindStudent();
        command.setLastName("Dobrovolskyiii");
        Result<StudentsFound> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(1, result.getValue().students().size());
    }

    @Test
    @DisplayName("Find students by email")
    public void testFindStudentsByEmail() {
        FindStudent command = new FindStudent();
        command.setEmail("ivan.dobrovolskyi@ukma.edu.");
        Result<StudentsFound> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(3, result.getValue().students().size());
    }

    @Test
    @DisplayName("Find students by fullname: first name")
    public void testFindStudentsByFullNameFirstName() {
        FindStudent command = new FindStudent();
        command.setFullName("Iva");
        Result<StudentsFound> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(3, result.getValue().students().size());
    }

    @Test
    @DisplayName("Find students by fullname: middle name")
    public void testFindStudentsByFullNameMiddleName() {
        FindStudent command = new FindStudent();
        command.setFullName("Oleksandrovych");
        Result<StudentsFound> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(2, result.getValue().students().size());
    }

    @Test
    @DisplayName("Find students by fullname: last name")
    public void testFindStudentsByFullNameLastName() {
        FindStudent command = new FindStudent();
        command.setFullName("Dobrovolskyiii");
        Result<StudentsFound> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(1, result.getValue().students().size());
    }

    @Test
    @DisplayName("Find students by multiple filters: Empty")
    public void testFindStudentsByMultipleFiltersEmpty() {
        FindStudent command = new FindStudent();
        command.setFirstName("Ivan1");
        command.setMiddleName("Oleksandrovychh");
        command.setLastName("Dobrovolskyiii");
        Result<StudentsFound> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertTrue(result.getValue().students().isEmpty());
    }

    @Test
    @DisplayName("Find students by multiple filters")
    public void testFindStudentsByMultipleFilters() {
        FindStudent command = new FindStudent();
        command.setFirstName("Iva");
        command.setLastName("Dobrovolskyi");
        Result<StudentsFound> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(2, result.getValue().students().size());
    }

}
