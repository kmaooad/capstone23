package edu.kmaooad.capstone23.students.events;

import edu.kmaooad.capstone23.students.dal.Student;

import java.util.List;

public class StudentRead {
    private final List<Student> students;

    public StudentRead(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
}
