package edu.kmaooad.capstone23.activities.events;

import java.util.List;

public class BulkCoursesUpdated {
    private final List<CourseUpdated> coursesList;

    public List<CourseUpdated> getCoursesList() {
        return coursesList;
    }

    public BulkCoursesUpdated(List<CourseUpdated> coursesList) {
        this.coursesList = coursesList;
    }
}
