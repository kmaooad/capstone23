package edu.kmaooad.capstone23.students.events;

import java.util.List;

public class StudentsCreated {
    private List<StudentCreated> students;

    public StudentsCreated(List<StudentCreated> students) {
        this.students = students;
    }

    public List<StudentCreated> getStudents() {
        return students;
    }

}
