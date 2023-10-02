package edu.kmaooad.capstone23.students.notification;

import edu.kmaooad.capstone23.mail.Notification;
import edu.kmaooad.capstone23.mail.NotificationBuilder;
import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateStudentNotificationBuilderTest {
    @Inject
    NotificationBuilder builder;

    @Test
    public void testNotification() {
        String email = "testMail@capstone23.ua";
        ObjectId studentId = new ObjectId("123456789012345678901234");
        NotifyStudent notifyStudent = new NotifyStudent.Create(studentId, email, "testName");

        Notification notification = builder.build(notifyStudent);

        Assertions.assertEquals(email, notification.getEmail());
        Assertions.assertEquals(NotificationBuilder.CreateStudent.SUBJECT, notification.getSubject());
        Assertions.assertEquals("Welcome testName, student with id: '123456789012345678901234' added to Capstone23", notification.getBody());
    }
}
