package edu.kmaooad.capstone23.students.events;

import org.bson.types.ObjectId;

import java.util.List;

public class StudentsCreated {
    private List<ObjectId> students;

    public StudentsCreated(List<ObjectId> students) {
        this.students = students;
    }

    public List<ObjectId> getStudents() {
        return students;
    }

}
