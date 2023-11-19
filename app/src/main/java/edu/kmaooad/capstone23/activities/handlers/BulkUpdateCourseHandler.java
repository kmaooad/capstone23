package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.BulkUpdateCourses;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.BulkCoursesUpdated;
import edu.kmaooad.capstone23.activities.events.CourseUpdated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@RequestScoped
public class BulkUpdateCourseHandler implements CommandHandler<BulkUpdateCourses, BulkCoursesUpdated> {
    @Inject
    private CourseRepository courseService;

    @Override
    public Result<BulkCoursesUpdated> handle(BulkUpdateCourses command) {
        List<Course> courses = command.getCoursesList().stream().map(courseCommand -> {
            var course = new Course();
            course.id = new ObjectId(courseCommand.getId());
            course.name = courseCommand.getName();
            return course;
        }).toList();

        var courseIds = courses.stream().map(course -> course.id.toHexString()).distinct().toList();
        var coursesInDb = courseService.findCoursesCountByIds(courseIds);
        if (coursesInDb != courses.size()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Some courses have incorrect IDs");
        }

        courseService.bulkUpdate(courses);

        List<CourseUpdated> coursesUpdated = courses.stream().map(course -> new CourseUpdated(course.id.toHexString(), course.name)).toList();

        var result = new BulkCoursesUpdated(coursesUpdated);
        return new Result<>(result);
    }
}
