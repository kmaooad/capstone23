package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.BulkDeleteCourses;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.events.BulkCoursesDeleted;
import edu.kmaooad.capstone23.activities.events.CourseDeleted;
import edu.kmaooad.capstone23.activities.services.CourseService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class BulkDeleteCoursesHandler implements CommandHandler<BulkDeleteCourses, BulkCoursesDeleted> {
    @Inject
    private CourseService courseService;

    @Override
    public Result<BulkCoursesDeleted> handle(BulkDeleteCourses command) {
        List<Course> courses = command.getCoursesList().stream().map(courseCommand -> {
            var course = new Course();
            course.id = courseCommand.getId();
            return course;
        }).toList();

        courseService.bulkDelete(courses);

        List<CourseDeleted> coursesDeleted = courses.stream()
                .map(course -> new CourseDeleted(course.id)).toList();

        var result = new BulkCoursesDeleted(coursesDeleted);
        return new Result<>(result);
    }
}
