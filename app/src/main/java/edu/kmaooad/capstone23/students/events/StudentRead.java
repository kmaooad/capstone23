package edu.kmaooad.capstone23.students.events;

import edu.kmaooad.capstone23.students.dal.Student;

import java.util.List;

public record StudentRead(List<Student> students) {
}
