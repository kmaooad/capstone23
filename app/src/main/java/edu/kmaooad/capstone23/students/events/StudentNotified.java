package edu.kmaooad.capstone23.students.events;

import org.bson.types.ObjectId;

public class StudentNotified {
    private ObjectId studentId;

    public StudentNotified(ObjectId studentId) {
        this.studentId = studentId;
    }

    public ObjectId getStudentId() {
        return studentId;
    }
}
