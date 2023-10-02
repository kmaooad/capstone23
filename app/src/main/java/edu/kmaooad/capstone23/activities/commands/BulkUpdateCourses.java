package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BulkUpdateCourses {
    @NotEmpty
    private List<@Valid UpdateCourse> coursesList;

    public List<UpdateCourse> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<UpdateCourse> coursesList) {
        this.coursesList = coursesList;
    }
}
