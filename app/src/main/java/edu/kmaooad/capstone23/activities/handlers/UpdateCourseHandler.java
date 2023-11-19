package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.UpdateCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.events.CourseUpdated;
import edu.kmaooad.capstone23.activities.services.CourseService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@RequestScoped
public class UpdateCourseHandler implements CommandHandler<UpdateCourse, CourseUpdated> {
    @Inject
    private CourseService courseService;

    @Override
    public Result<CourseUpdated> handle(UpdateCourse command) {
        Optional<Course> course = courseService.findById(command.getId());
        if (course.isEmpty())
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Course with this ID does not exist");

        Course courseItem = course.get();
        courseItem.name = command.getName();

        courseService.update(courseItem);

        return new Result<>(new CourseUpdated(courseItem.id.toString(), courseItem.name));
    }
}