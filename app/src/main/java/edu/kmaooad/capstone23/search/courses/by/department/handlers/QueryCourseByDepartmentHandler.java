package edu.kmaooad.capstone23.search.courses.by.department.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.search.QueryEssenceHandler;
import edu.kmaooad.capstone23.search.courses.by.department.events.CourseQueriedByDepartment;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryCourseByDepartmentHandler extends QueryEssenceHandler<
        Course,
        Department,
        CourseRepository,
        DepartmentsRepository,
        CourseQueriedByDepartment
        >
{
    public QueryCourseByDepartmentHandler() {
        super (course -> course.id, department -> department.id, CourseQueriedByDepartment::new);
    }
}
