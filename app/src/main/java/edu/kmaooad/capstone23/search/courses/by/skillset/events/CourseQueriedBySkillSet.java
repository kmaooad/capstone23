package edu.kmaooad.capstone23.search.courses.by.skillset.events;

import edu.kmaooad.capstone23.activities.dal.Course;

import java.util.List;

public record CourseQueriedBySkillSet(List<Course> queriedCourses) {
}
