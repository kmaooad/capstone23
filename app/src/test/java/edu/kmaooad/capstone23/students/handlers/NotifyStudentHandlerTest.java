package edu.kmaooad.capstone23.students.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import edu.kmaooad.capstone23.students.events.StudentNotified;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class NotifyStudentHandlerTest {
    @Inject
    NotifyStudentHandler handler;

    @Test
    @DisplayName("Notification send: Successful")
    public void testNotificationSendSuccess() {
        String email = "testMail@capstone23.ua";
        ObjectId studentId = new ObjectId("123456789012345678901234");
        NotifyStudent notifyStudent = new NotifyStudent.Create(studentId, email, "testName");
        Result<StudentNotified> result = handler.handle(notifyStudent);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(studentId, result.getValue().studentId());
    }

    @Test
    @DisplayName("Notification send: Incorrect Email")
    public void testNotificationSendIncorrectEmail() {
        String email = "testMail";
        ObjectId studentId = new ObjectId("123456789012345678901234");
        NotifyStudent notifyStudent = new NotifyStudent.Create(studentId, email, "testName");
        Result<StudentNotified> result = handler.handle(notifyStudent);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }

    @Test
    @DisplayName("Notification send: Student not found")
    public void testNotificationSendNotFound() {
        String email = "testMail@capstone23.ua";
        ObjectId studentId = null;
        NotifyStudent notifyStudent = new NotifyStudent.Create(studentId, email, "testName");
        Result<StudentNotified> result = handler.handle(notifyStudent);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.NOT_FOUND, result.getErrorCode());
    }
}
