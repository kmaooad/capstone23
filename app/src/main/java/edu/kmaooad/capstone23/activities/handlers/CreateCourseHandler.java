package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.activities.services.CourseService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;

@RequestScoped
public class CreateCourseHandler implements CommandHandler<CreateCourse, CourseCreated> {
    @Inject
    CourseService courseService;

    @Override
    public Result<CourseCreated> handle(CreateCourse command) {
        Course course = new Course();
        course.name = command.getName();
        course.tags = new ArrayList<>();

        courseService.insert(course);

        var result = new CourseCreated(course.id.toHexString());
        return new Result<>(result);
    }
}
