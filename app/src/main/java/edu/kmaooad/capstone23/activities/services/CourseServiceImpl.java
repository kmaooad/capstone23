package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CourseServiceImpl implements CourseService {
    @Inject
    private CourseRepository courseRepository;
    @Override
    public Course insert(Course course) {
        return courseRepository.insert(course);
    };
}
