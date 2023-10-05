package edu.kmaooad.capstone23.search.courses.by.skill.events;

import edu.kmaooad.capstone23.activities.dal.Course;

import java.util.List;

public record CourseQueriedBySkill (List<Course> queriedCourses){
}