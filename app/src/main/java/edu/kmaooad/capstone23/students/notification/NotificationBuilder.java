package edu.kmaooad.capstone23.students.notification;

import edu.kmaooad.capstone23.students.commands.notifications.NotifyStudent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationBuilder {

    public Notification build(NotifyStudent student) {
        if (student instanceof NotifyStudent.Create) return CreateStudent.build((NotifyStudent.Create) student);
        if (student instanceof NotifyStudent.Update) return UpdateStudent.build((NotifyStudent.Update) student);
        else return null;
    }

    static class CreateStudent {
        static final String SUBJECT = "Welcome to Capstone23";

        public static Notification build(NotifyStudent.Create student) {
            String body = String.format(
                    "Welcome %s, student with id: '%s' added to Capstone23",
                    student.getFirstName(),
                    student.getStudentId().toString()
            );
            return new Notification(student.getEmail(), SUBJECT, body);
        }
    }

    static class UpdateStudent {
        static final String SUBJECT = "Updated info in Capstone23";

        public static Notification build(NotifyStudent.Update student) {
            String stInfo = "";
            if (student.getStudent().firstName != null) {
                stInfo += "\nFirst name: " + student.getStudent().firstName;
            }
            if (student.getStudent().middleName != null) {
                stInfo += "\nMiddle name: " + student.getStudent().middleName;
            }

            if (student.getStudent().lastName != null) {
                stInfo += "\nLast name: " + student.getStudent().lastName;
            }
            String body = String.format(
                    "Hello %s, student with id: '%s' was updated in Capstone23: New information: \n%s",
                    student.getStudent().firstName,
                    student.getStudentId().toString(),
                    stInfo +  "\nDOB: " + student.getStudent().DOBTimestamp
            );
            return new Notification(student.getEmail(), SUBJECT, body);
        }
    }
}
