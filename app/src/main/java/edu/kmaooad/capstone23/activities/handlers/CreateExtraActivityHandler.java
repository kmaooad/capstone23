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
public class CreateExtraActivityHandler implements CommandHandler<CreateCourse, CourseCreated> {
    @Inject
    private CourseRepository courseRepository;

    @Override
    public Result<CourseCreated> handle(CreateCourse command) {
        Activity activity = new Activity();
        activity.name = command.getName();


        var result = new CourseCreated(course.id.toHexString());
        return new Result<>(result);
    }
}
