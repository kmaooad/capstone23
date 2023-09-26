package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BulkCreateCourses {
    @NotEmpty
    private List<@Valid CreateCourse> coursesList;

    public List<CreateCourse> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<CreateCourse> coursesList) {
        this.coursesList = coursesList;
    }
}
