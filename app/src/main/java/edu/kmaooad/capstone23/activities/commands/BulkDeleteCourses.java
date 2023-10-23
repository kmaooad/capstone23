package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BulkDeleteCourses {
    @NotEmpty
    private List<@Valid DeleteCourse> coursesList;

    public List<DeleteCourse> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<DeleteCourse> coursesList) {
        this.coursesList = coursesList;
    }
}
