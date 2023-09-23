package edu.kmaooad.capstone23.activities.events;


import java.util.List;

public class BulkCoursesCreated {

    private final List<CourseCreated> coursesList;

    public List<CourseCreated> getCoursesList() {
        return coursesList;
    }

    public BulkCoursesCreated(List<CourseCreated> coursesList) {
        this.coursesList = coursesList;
    }
}
