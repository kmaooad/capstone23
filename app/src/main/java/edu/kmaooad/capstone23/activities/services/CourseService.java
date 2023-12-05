package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.search.Listable;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;


import java.util.List;

import java.util.Optional;

public interface CourseService extends Listable<Course> {
    public Course insert(Course course);
    public Optional<Course> findById(String id);
    public Course find(String name);
    public Course update(Course course);
    public void delete(Optional<Course> course);
    public void bulkInsert(List<Course> courses);
    void bulkDelete(List<Course> courses);
    void findCoursesCountByIds(List<String> courseIds);
    void bulkUpdate(List<Course> courses);
}
