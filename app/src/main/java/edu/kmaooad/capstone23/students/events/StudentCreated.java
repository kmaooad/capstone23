package edu.kmaooad.capstone23.students.events;

public class StudentCreated {
    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public StudentCreated(String studentId) {
        this.studentId = studentId;
    }
}