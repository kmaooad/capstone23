package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.DeleteCourse;
import edu.kmaooad.capstone23.activities.events.CourseDeleted;
import edu.kmaooad.capstone23.activities.services.CourseService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteCourseHandler implements CommandHandler<DeleteCourse, CourseDeleted> {
    @Inject
    private CourseService courseService;

    @Override
    public Result<CourseDeleted> handle(DeleteCourse command) {
        var courseId = command.getId();
        var foundCourse = courseService.findById(courseId.toHexString());
        if(foundCourse != null) {
            courseService.delete(foundCourse);
            var result = new CourseDeleted(courseId);
            return new Result<>(result);
        } else {
            return new Result<>(ErrorCode.EXCEPTION,
                    "Unable to delete a non-existent course.");
        }
    }
}