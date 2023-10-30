package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.Course;

import java.util.Optional;

public interface CourseService {
    public Course insert(Course course);
    public Optional<Course> findById(String id);
    public Course update(Course course);
    public Course find(String field, String value);
}
