package edu.kmaooad.capstone23.students.events;

import java.util.List;

public class StudentsUpdated {
    private List<StudentUpdated> students;

    public StudentsUpdated(List<StudentUpdated> students) {
        this.students = students;
    }

    public List<StudentUpdated> getStudents() {
        return students;
    }

}
