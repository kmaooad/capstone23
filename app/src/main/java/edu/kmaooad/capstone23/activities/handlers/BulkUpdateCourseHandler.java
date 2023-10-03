package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.BulkCreateCourses;
import edu.kmaooad.capstone23.activities.commands.BulkUpdateCourses;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.BulkCoursesCreated;
import edu.kmaooad.capstone23.activities.events.BulkCoursesUpdated;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.activities.events.CourseUpdated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@RequestScoped
public class BulkUpdateCourseHandler implements CommandHandler<BulkUpdateCourses, BulkCoursesUpdated> {
    @Inject
    private CourseRepository repository;

    @Override
    public Result<BulkCoursesUpdated> handle(BulkUpdateCourses command) {
        List<Course> courses = command.getCoursesList().stream().map(courseCommand -> {
            var course = new Course();
            course.id = new ObjectId(courseCommand.getId());
            course.name = courseCommand.getName();
            return course;
        }).toList();

        repository.bulkUpdate(courses);

        List<CourseUpdated> coursesUpdated = courses.stream().map(course -> new CourseUpdated(course.id.toHexString(), course.name)).toList();

        var result = new BulkCoursesUpdated(coursesUpdated);
        return new Result<>(result);
    }
}
