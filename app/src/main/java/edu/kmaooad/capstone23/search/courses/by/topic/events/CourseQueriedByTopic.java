package edu.kmaooad.capstone23.search.courses.by.topic.events;

import edu.kmaooad.capstone23.activities.dal.Course;

import java.util.List;

public record CourseQueriedByTopic (List<Course> queriedCourses) {}

