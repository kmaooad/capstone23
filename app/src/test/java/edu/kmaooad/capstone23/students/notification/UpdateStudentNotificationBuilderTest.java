package edu.kmaooad.capstone23.students.notification;

import edu.kmaooad.capstone23.mail.service.Notification;
import edu.kmaooad.capstone23.mail.service.NotificationBuilder;
import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateStudentNotificationBuilderTest {
    @Inject
    NotificationBuilder builder;

    @Test
    public void testNotification() {
        Student updatedStudent = new Student();
        updatedStudent.firstName = "testName";
        updatedStudent.email = "testMail@capstone23.ua";
        updatedStudent.id = new ObjectId("123456789012345678901234");
        updatedStudent.lastName = "lastName";
        NotifyStudent notifyStudent = new NotifyStudent.Update(updatedStudent);

        Notification notification = builder.build(notifyStudent);

        Assertions.assertEquals(updatedStudent.email, notification.getEmail());
        Assertions.assertEquals(NotificationBuilder.UpdateStudent.SUBJECT, notification.getSubject());
        Assertions.assertEquals(
                "Hello testName, student with id: '123456789012345678901234' was updated in Capstone23: New information: \n\nFirst name: testName\nLast name: lastName\nDOB: 0",
                notification.getBody());
    }
}
