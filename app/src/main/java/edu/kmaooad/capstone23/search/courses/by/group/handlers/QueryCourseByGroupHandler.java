package edu.kmaooad.capstone23.search.courses.by.group.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.search.QueryEssenceHandler;
import edu.kmaooad.capstone23.search.courses.by.group.events.CourseQueriedByGroup;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueryCourseByGroupHandler extends QueryEssenceHandler<
        Course,
        Group,
        CourseRepository,
        GroupsRepository,
        CourseQueriedByGroup
        >
{
    public QueryCourseByGroupHandler() {
        super (course -> course.id, group -> group.id, CourseQueriedByGroup::new);
    }
}