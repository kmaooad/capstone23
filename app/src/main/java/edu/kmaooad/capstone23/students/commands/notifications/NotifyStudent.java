package edu.kmaooad.capstone23.students.commands.notifications;

import org.bson.types.ObjectId;

public abstract class NotifyStudent {
    private final ObjectId studentId;
    private final String email;

    public NotifyStudent(ObjectId studentId, String email) {
        this.studentId = studentId;
        this.email = email;
    }

    public ObjectId getStudentId() {
        return studentId;
    }

    public String getEmail() {
        return email;
    }

    public static class Create extends NotifyStudent {
        private final String firstName;

        public Create(ObjectId studentId, String email, String firstName) {
            super(studentId, email);
            this.firstName = firstName;
        }

        public String getFirstName() {
            return firstName;
        }
    }
}
