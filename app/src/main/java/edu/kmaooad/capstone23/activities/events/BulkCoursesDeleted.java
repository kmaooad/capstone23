package edu.kmaooad.capstone23.activities.events;

import java.util.List;

public class BulkCoursesDeleted {

    private final List<CourseDeleted> coursesList;

    public List<CourseDeleted> getCoursesList() {
        return coursesList;
    }

    public BulkCoursesDeleted(List<CourseDeleted> coursesList) {
        this.coursesList = coursesList;
    }
}
