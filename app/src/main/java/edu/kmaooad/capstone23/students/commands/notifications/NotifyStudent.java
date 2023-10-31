package edu.kmaooad.capstone23.students.commands.notifications;

import edu.kmaooad.capstone23.students.dal.Student;
import jakarta.validation.constraints.Email;
import org.bson.types.ObjectId;

public abstract class NotifyStudent {
    private final ObjectId studentId;
    @Email
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

    public static class Update extends NotifyStudent {
        public Student getStudent() {
            return student;
        }

        private Student student;
        public Update(Student student) {
            super(student.id, student.email);
            this.student = student;
        }
    }
}
