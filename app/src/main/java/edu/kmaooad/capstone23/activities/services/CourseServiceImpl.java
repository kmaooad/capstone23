package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class CourseServiceImpl implements CourseService {
    @Inject
    private CourseRepository courseRepository;
    @Override
    public Course insert(Course course) {
        return courseRepository.insert(course);
    }

    @Override
    public Optional<Course> findById(String id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course update(Course course) {
        courseRepository.update(course);
        return course;
    }

    @Override
    public Course find(String field, String value) {
        return courseRepository.find(field , value).firstResult();
    }
}
