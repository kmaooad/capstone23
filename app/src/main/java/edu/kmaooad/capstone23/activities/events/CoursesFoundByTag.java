package edu.kmaooad.capstone23.activities.events;

import edu.kmaooad.capstone23.activities.dal.Course;

import java.util.List;

public class CoursesFoundByTag {
    public List<Course> courses;
    public CoursesFoundByTag(List<Course> courses) {
        this.courses = courses;
    }

}
