package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.Course;


import java.util.List;

import java.util.Optional;

public interface CourseService {
    public Course insert(Course course);
    public Optional<Course> findById(String id);
    public Course update(Course course);
    public void delete(Optional<Course> course);
    public void bulkInsert(List<Course> courses);
    void bulkDelete(List<Course> courses);
    void findCoursesCountByIds(List<String> courseIds);
    void bulkUpdate(List<Course> courses);
}
