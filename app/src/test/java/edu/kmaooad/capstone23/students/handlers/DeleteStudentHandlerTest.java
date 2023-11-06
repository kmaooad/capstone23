package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.DeleteStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteStudentHandlerTest {

    @Inject
    StudentRepository studentRepository;

    @Inject
    DeleteStudentHandler deleteStudentHandler;

    @Test
    @DisplayName("Delete Student: Basic")
    public void testDeleteTag() {
        var student = new Student();
        student.email = "mail";
        student.firstName = "Test";
        student.middleName = "MiddleTest";
        student.lastName = "TestLastName";
        student.DOBTimestamp = 10;
        studentRepository.persist(student);
        DeleteStudent deleteStudentCommand = new DeleteStudent();
        deleteStudentCommand.setObjectId(student.id);

        Result<StudentDeleted> result = deleteStudentHandler.handle(deleteStudentCommand);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(student.id, result.getValue().getObjectId());
    }

    @Test
    @DisplayName("Delete Student: Nonexistent")
    public void testDeleteNonexistentTag() {
        DeleteStudent command = new DeleteStudent();
        command.setObjectId(new ObjectId());

        var result = deleteStudentHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @AfterEach
    public void teardown() {
        studentRepository.deleteAll();
    }
}
