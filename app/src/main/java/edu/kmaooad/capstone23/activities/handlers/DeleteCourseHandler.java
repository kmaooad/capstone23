package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.DeleteCourse;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.CourseDeleted;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteCourseHandler implements CommandHandler<DeleteCourse, CourseDeleted> {
    @Inject
    CourseRepository repository;

    @Override
    public Result<CourseDeleted> handle(DeleteCourse command) {
        var courseId = command.getId();
        var foundCourse = repository.findById(courseId);
        if(foundCourse != null) {
            repository.delete(foundCourse);
            var result = new CourseDeleted(courseId);
            return new Result<>(result);
        } else {
            return new Result<>(ErrorCode.EXCEPTION,
                    "Unable to delete a non-existent course.");
        }
    }
}