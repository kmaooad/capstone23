package edu.kmaooad.capstone23.mail.service;

import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationBuilder {

    public Notification build(NotifyStudent student) {
        if (student instanceof NotifyStudent.Create) return CreateStudent.build((NotifyStudent.Create) student);
        else return null;
    }

    public static class CreateStudent {
        public static final String SUBJECT = "Welcome to Capstone23";
        public static Notification build(NotifyStudent.Create student) {
            String body = String.format(
                    "Welcome %s, student with id: '%s' added to Capstone23",
                    student.getFirstName(),
                    student.getStudentId().toString()
            );
            return new Notification(student.getEmail(), SUBJECT, body);
        }
    }
}
