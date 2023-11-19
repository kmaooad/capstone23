package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.BulkCreateCourses;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.events.BulkCoursesCreated;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.activities.services.CourseService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
@RequestScoped
public class BulkCreateCoursesHandler implements CommandHandler<BulkCreateCourses, BulkCoursesCreated> {

    @Inject
    private CourseService courseService;

    @Override
    public Result<BulkCoursesCreated> handle(BulkCreateCourses command) {
        List<Course> courses = command.getCoursesList().stream().map(courseCommand -> {
            var course = new Course();
            course.name = courseCommand.getName();
            course.tags = new ArrayList<>();
            return course;
        }).toList();

        courseService.bulkInsert(courses);

        List<CourseCreated> coursesCreated = courses.stream().map(course -> new CourseCreated(course.id.toHexString())).toList();

        var result = new BulkCoursesCreated(coursesCreated);
        return new Result<>(result);
    }
}
