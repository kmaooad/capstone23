package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateCourseHandler implements CommandHandler<CreateCourse, CourseCreated> {
    @Inject
    private CourseRepository courseRepository;

    @Override
    public Result<CourseCreated> handle(CreateCourse command) {
        Course course = new Course();
        course.name = command.getName();

        courseRepository.insert(course);

        var result = new CourseCreated(course.id.toHexString());
        return new Result<>(result);
    }
}
