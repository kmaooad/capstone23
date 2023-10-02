package edu.kmaooad.capstone23.search.courses.by.group.events;

import edu.kmaooad.capstone23.activities.dal.Course;

import java.util.List;

public record CourseQueriedByGroup (List<Course> queriedCourses) {}
