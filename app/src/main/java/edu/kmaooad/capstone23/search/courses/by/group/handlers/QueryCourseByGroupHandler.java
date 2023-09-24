package edu.kmaooad.capstone23.search.courses.by.group.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.relations.dal.QueryableRelationRepository;
import edu.kmaooad.capstone23.search.QueryEssenceHandler;
import edu.kmaooad.capstone23.search.courses.by.group.events.CourseQueriedByGroup;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class QueryCourseByGroupHandler extends QueryEssenceHandler<
        Course,
        Group,
        CourseRepository,
        GroupsRepository,
        CourseQueriedByGroup
        > {
    @Inject
    public QueryCourseByGroupHandler(QueryableRelationRepository relationRepository,
                                     GroupsRepository groupRepository,
                                     CourseRepository courseRepository) {
        super  (course -> course.id,
                group -> group.id,
                CourseQueriedByGroup::new,
                relationRepository,
                courseRepository,
                groupRepository);
    }
}