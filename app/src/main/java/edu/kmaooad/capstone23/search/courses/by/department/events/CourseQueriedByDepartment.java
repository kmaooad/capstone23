package edu.kmaooad.capstone23.search.courses.by.department.events;

import edu.kmaooad.capstone23.activities.dal.Course;

import java.util.List;

public record CourseQueriedByDepartment (List<Course> queriedCourses) {}
